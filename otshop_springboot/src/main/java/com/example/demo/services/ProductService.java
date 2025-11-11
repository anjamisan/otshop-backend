package com.example.demo.services;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import model.*;


import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.dto.PurchaseDto;
import com.example.demo.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductimageRepository productImageRepository;
    @Autowired
    AgesexRepository agesexRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AgesexHasCategoryRepository agesexHasCategoryRepository;
    
    @Autowired
    PurchaseRepository purchaseRepository;

    
    @Transactional
    public ProductResponseDto createProduct(ProductCreateDto dto) {
        Agesex agesex = agesexRepository.findById(dto.getAgesexId())
                .orElseThrow(() -> new IllegalArgumentException("Agesex not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        boolean validCombination = agesexHasCategoryRepository.existsByAgesexAndCategory(agesex, category);
        if (!validCombination) throw new IllegalArgumentException("Invalid Agesex–Category combination");

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setAgesex(agesex);
        product.setCategory(category);
        product.setCondition(ConditionType.valueOf(dto.getCondition()));

        //prvo sacuvamo product bez slika kako bismo mogli slike pojedinacno
        Product saved = productRepository.save(product);
        System.out.println("Product saved with ID: " + saved.getIdProduct());

        List<Productimage> images = new ArrayList<>();
        if (dto.getImages() != null) {
            for (MultipartFile file : dto.getImages()) {
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, filename);
                System.out.println("Saving file: " + filePath.toAbsolutePath());

                try {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), e);
                }

                Productimage image = new Productimage();
                image.setUrl("/uploads/" + filename);
                image.setProduct(saved); 
                productImageRepository.save(image);
                images.add(image);
                System.out.println("Saved image " + filename);
            }
        }

        // Apdejt na product, dodsj slike
        saved.setProductimages(images);
        productRepository.save(saved);

        //ResponseDto? 
        ProductResponseDto resp = new ProductResponseDto();
        resp.setIdProduct(saved.getIdProduct());
        resp.setProductName(saved.getProductName());
        resp.setPrice(saved.getPrice());
        resp.setImageUrls(images.stream().map(Productimage::getUrl).toList());
        return resp;
    }

    
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }
    
    public ProductDto getProductById(int id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Non existent id"));
        return new ProductDto(product);
    }
    
    @Transactional
    public ProductDto updateProduct(int id, ProductUpdateDto dto) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        
        if (product.isSold()) {
            throw new IllegalStateException("Cannot edit a sold product");
        }
        
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        
        Product saved = productRepository.save(product);
        return new ProductDto(saved);
    }

    @Transactional
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        
        if (product.isSold()) {
            throw new IllegalStateException("Cannot delete a sold product");
        }
        productRepository.delete(product);
    }
    
    public PurchaseDto getPurchaseByProductId(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Purchase purchase = purchaseRepository.findByProduct(product)
                .orElseThrow(() -> new IllegalArgumentException("No purchase found for this product."));

        return new PurchaseDto(purchase);
    }
}
