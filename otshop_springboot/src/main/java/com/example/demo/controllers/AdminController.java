package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.dto.UserSummaryDto;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminController {
	
	@Autowired
	ProductService productService;
    
    @Autowired
    UserService userService;


    @PostMapping(value = "/products/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(
    		@RequestParam("productName") @NotBlank(message = "Product name cannot be blank") String productName,

            @RequestParam(value = "description", required = false) String description,

            @RequestParam("condition") @NotBlank(message = "Condition must be provided") String condition,

            @RequestParam("price") @NotNull(message = "Price must be provided")
            @Min(value = 1, message = "Price must be at least 1") Integer price,

            @RequestParam("ageSexId") @NotNull(message = "Age/Sex ID cannot be null") Integer ageSexId,

            @RequestParam("categoryId") @NotNull(message = "Category ID cannot be null") Integer categoryId,

            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) {
    	ProductCreateDto dto = new ProductCreateDto(productName, condition, description, price, ageSexId, categoryId, images);
        ProductResponseDto created = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable int id,
            @RequestBody ProductUpdateDto dto) {
        ProductDto updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserSummaryDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUserSummaries());
    }
}

