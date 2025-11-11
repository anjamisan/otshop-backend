package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PurchaseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserProductDto;
import com.example.demo.dto.UserSummaryDto;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.PurchaseRepository;
import com.example.demo.repositories.SavedProductRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;
import model.Product;
import model.Purchase;
import model.Savedproduct;
import model.SavedproductPK;
import model.User;

@Service
public class UserService {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SavedProductRepository savedRepository;

	//nadji usera
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
    
    //lista svih usera za admina
    public List<UserSummaryDto> getAllUserSummaries() {
        return userRepository.findAllWithPurchaseCount();
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //         FAVORITI            //

    //user lajkuje proizvod
    @Transactional
    public void addToFavourites(UserProductDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (savedRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalStateException("Product already in favourites");
        }

        Savedproduct saved = new Savedproduct();
        SavedproductPK pk = new SavedproductPK();
        pk.setUserIdUser(user.getIdUser());
        pk.setProductIdProduct(product.getIdProduct());

        saved.setId(pk);
        saved.setUser(user);
        saved.setProduct(product);

        savedRepository.save(saved);
    }

    // svi favoriti za usera
    @Transactional
    public List<UserProductDto> getFavouritesByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Savedproduct> saved =  savedRepository.findByUser(user);
        return saved.stream().map(UserProductDto::new).collect(Collectors.toList());
    }

    // ukloni iz favorita
    @Transactional
    public void removeFromFavourites(int userId, int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (!savedRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalStateException("Product not in favourites");
        }

        savedRepository.deleteByUserAndProduct(user, product);
    }
    
    //da li je lajkovan
    public boolean isProductLikedByUser(int userId, int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        
        return savedRepository.existsByUserAndProduct(user, product);
    }
    
}
