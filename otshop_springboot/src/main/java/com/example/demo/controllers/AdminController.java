package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/products")
@Validated
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(
    		@RequestParam("productName") String productName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("condition") String condition,
            @RequestParam("price") Integer price,
            @RequestParam("ageSexId") Integer ageSexId,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam(value = "images", required = false) List<MultipartFile> image
    ) {
    	ProductCreateDto dto = new ProductCreateDto(productName, condition, description, price, ageSexId, categoryId, image);
        ProductResponseDto created = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

