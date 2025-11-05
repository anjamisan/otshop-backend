package com.example.demo.services;


import jakarta.transaction.Transactional;
import model.*;

import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AgesexRepository agesexRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AgesexHasCategoryRepository agesexHasCategoryRepository;

    
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

        //dodeli svakoj slici unikatan id i sacuva
        List<Productimage> images = new ArrayList<>();
        if (dto.getImages() != null) {
            for (MultipartFile file : dto.getImages()) {
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, filename);
                try {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), e);
                }

                //u bazu ide samo url
                Productimage image = new Productimage();
                image.setUrl("/uploads/" + filename); // this will be accessible via static resource
                image.setProduct(product);
                images.add(image);
            }
        }

        product.setProductimages(images);
        Product saved = productRepository.save(product);

        //response DTO
        ProductResponseDto resp = new ProductResponseDto();
        resp.setIdProduct(saved.getIdProduct());
        resp.setProductName(saved.getProductName());
        resp.setPrice(saved.getPrice());
        resp.setImageUrls(saved.getProductimages()
                .stream().map(Productimage::getUrl).toList()); //u responsu saljemo samo urlove
        return resp;
    }
}
