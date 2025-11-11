package com.example.demo.controllers;

import com.example.demo.dto.UserProductDto;
import com.example.demo.services.UserService;

import model.Savedproduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
@CrossOrigin(origins = "http://localhost:4200")
public class SavedProductController {

    @Autowired
    private UserService service;

    @PostMapping("/add")
    public ResponseEntity<String> addToFavourites(@RequestBody UserProductDto dto) {
        service.addToFavourites(dto);
        return ResponseEntity.ok("Product added to favourites");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavourite(
            @RequestParam int userId,
            @RequestParam int productId) {
        service.removeFromFavourites(userId, productId);
        return ResponseEntity.ok("Product removed from favourites!");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserProductDto>> getUserFavourites(@PathVariable int userId) {
        return ResponseEntity.ok(service.getFavouritesByUser(userId));
    }
    
    @GetMapping("/is-liked")
    public ResponseEntity<Boolean> isProductLikedByUser(@RequestParam int userId,
            @RequestParam int productId) {
        
        boolean liked = service.isProductLikedByUser(userId, productId);
        return ResponseEntity.ok(liked);
    }
}
