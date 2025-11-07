package com.example.demo.controllers;

import model.Agesex;
import model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AddCategoryDto;
import com.example.demo.dto.AgesexDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.services.AgesexCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/agesex")
public class AgesexCategoryController {

	@Autowired
	AgesexCategoryService service;

	@GetMapping()
	public List<AgesexDto> getAgesex() {
		return service.getAgesex();
	}

	// prikazi dostupne kategorije za odabrani pol. ovo za admina kad unosi artikle
	@GetMapping("/{agesexId}/categories")
	public List<CategoryDto> getCategoriesForAgesex(@PathVariable int agesexId) {
		return service.getCategoriesForAgesex(agesexId);
	}
	
	@PostMapping("/add-category")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addCategory(@RequestBody AddCategoryDto dto) {
        service.addCategory(dto);
        return ResponseEntity.ok("Category added successfully");
    }
}
