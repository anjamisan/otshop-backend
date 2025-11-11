package com.example.demo.repositories;

import model.Product;
import model.Purchase;
import model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUser_IdUser(int userId);
    
    Optional<Purchase> findByProduct(Product product);
    
    List<Purchase> findByUser(User user);

    boolean existsByUserAndProduct(User user, Product product);

}
