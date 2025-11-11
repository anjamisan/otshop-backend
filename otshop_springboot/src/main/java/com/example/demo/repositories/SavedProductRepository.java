package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Agesex;
import model.Product;
import model.Savedproduct;
import model.SavedproductPK;
import model.User;

public interface SavedProductRepository extends JpaRepository<Savedproduct, SavedproductPK>{

	boolean existsByUserAndProduct(User user, Product product);

	List<Savedproduct> findByUser(User user);

	void deleteByUserAndProduct(User user, Product product);

}
