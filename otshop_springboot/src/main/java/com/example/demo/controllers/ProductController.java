
package com.example.demo.controllers;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.dto.PurchaseDto;
import com.example.demo.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200") // for Angular
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //moze i za neulogovane
    @GetMapping("/preview")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        try {
            ProductDto productDto = productService.getProductById(id);
            return ResponseEntity.ok(productDto);
        } catch (IllegalArgumentException e) {
            System.out.println("ne postoji product sa tim id-em");
            throw e;
        } 
    }
    
    
    @GetMapping("/{productId}/purchase")
    public ResponseEntity<PurchaseDto> getPurchaseByProductId(@PathVariable int productId) {
        PurchaseDto dto = productService.getPurchaseByProductId(productId);
        return ResponseEntity.ok(dto);
    }

}
