package com.example.demo.services;

import com.example.demo.dto.PurchaseDto;
import com.example.demo.dto.UserProductDto;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.PurchaseRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;
import model.Product;
import model.Purchase;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    
  //vrati sve kupovine za usera
    public List<PurchaseDto> getPurchasesByUserId(int userId) {
        List<Purchase> p =  purchaseRepository.findByUser_IdUser(userId);
        return p.stream().map(PurchaseDto::new).collect(Collectors.toList());
    }
    
    //kupi proizvod
    @Transactional
    public void buyProduct(UserProductDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        //zabrani duplo kupovanje
        if (purchaseRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalStateException("You already purchased this product.");
        }

        // ako je vec sold
        if (product.isSold()) {
            throw new IllegalStateException("Product already sold.");
        }

        // novi purchase
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setTimestamp(new Date());

        //sacuvaj
        purchaseRepository.save(purchase);

        System.out.println("Purchase successful: " + user.getUsername() + " bought " + product.getProductName());
    }

    //vrati kupovinu za product
    public Purchase getPurchaseByProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return purchaseRepository.findByProduct(product)
                .orElseThrow(() -> new IllegalArgumentException("Purchase not found for this product"));
    }
}
