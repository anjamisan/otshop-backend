package com.example.demo.services;

import model.Agesex;
import model.Category;
import model.AgesexHasCategory;
import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.AddCategoryDto;
import com.example.demo.dto.AgesexDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.AgesexHasCategoryRepository;
import com.example.demo.repositories.AgesexRepository;
import com.example.demo.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgesexCategoryService {
	
	@Autowired
    CategoryRepository categoryRepository;
	@Autowired
	AgesexRepository agesexRepository;
	@Autowired
	AgesexHasCategoryRepository agesexHasCategoryRepository;

	public List<AgesexDto> getAgesex() {
		return agesexRepository.findAll().stream().map(AgesexDto::fromEntity).collect(Collectors.toList());
	}

	public List<CategoryDto> getCategoriesForAgesex(int agesexId) {
		Agesex agesex = agesexRepository.findById(agesexId)
				.orElseThrow(() -> new IllegalArgumentException("Agesex not found"));

		List<AgesexHasCategory> pairs = agesexHasCategoryRepository.findByAgesex(agesex);

		return pairs.stream().map(AgesexHasCategory::getCategory).map(CategoryDto::fromEntity).collect(Collectors.toList());
	}
	
	public void addCategory(AddCategoryDto dto) {
        // prvo samo kategoriju
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        category = categoryRepository.save(category);

        // dobavim agesex po kljucu
        Agesex agesex = agesexRepository.findById(dto.getAgesexId())
                .orElseThrow(() -> new IllegalArgumentException("Agesex not found"));

        // povezem ih u trecoj tabeli
        AgesexHasCategory link = new AgesexHasCategory();
        link.setAgesex(agesex);
        link.setCategory(category);
        agesexHasCategoryRepository.save(link);
    }
}
