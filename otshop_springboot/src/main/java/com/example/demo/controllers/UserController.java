package com.example.demo.controllers;

import com.example.demo.dto.PurchaseDto;
import com.example.demo.dto.UserProductDto;
import com.example.demo.dto.UserSummaryDto;
import com.example.demo.services.PurchaseService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;
    
    @Autowired
    PurchaseService purchaseService;
    
    

    @GetMapping("/{id}/purchases") //ovo moze i za admina i za usera
    public ResponseEntity<List<PurchaseDto>> getUserPurchases(@PathVariable int id) {
        List<PurchaseDto> purchases = purchaseService.getPurchasesByUserId(id);
        return ResponseEntity.ok(purchases);
    }
    
    @PostMapping("/buy") //samo user
    public ResponseEntity<String> buyProduct(@RequestBody UserProductDto dto) {
    	purchaseService.buyProduct(dto);
        return ResponseEntity.ok("Purchase successful");
    }

}
