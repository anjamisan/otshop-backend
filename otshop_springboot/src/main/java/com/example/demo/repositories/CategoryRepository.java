package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Optional<Category> findById(Integer categoryId);
	
	Optional<Category> findByCategoryName(String categoryName);
	
}
