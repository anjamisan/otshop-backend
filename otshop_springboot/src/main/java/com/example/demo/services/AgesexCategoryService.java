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
import java.util.Optional;
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
        // Da bude sve malim slovima
        String normalizedName = dto.getCategoryName().trim().toLowerCase();

        // proveri jel vec postoji
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(normalizedName);

        if (existingCategory.isPresent()) {
            // proveri jel povezana vec sa istim agesex
            Agesex agesex = agesexRepository.findById(dto.getAgesexId())
                    .orElseThrow(() -> new IllegalArgumentException("Agesex not found"));

            boolean alreadyLinked = agesexHasCategoryRepository
                    .findByAgesexAndCategory(agesex, existingCategory.get())
                    .isPresent();

            if (alreadyLinked) {
                throw new IllegalArgumentException("This category already exists for the selected group.");
            }

            // ako nije povezana, napravi poveynicu
            AgesexHasCategory newLink = new AgesexHasCategory();
            newLink.setAgesex(agesex);
            newLink.setCategory(existingCategory.get());
            agesexHasCategoryRepository.save(newLink);
            return;
        }

        // napravi novu kategoriju ako ne postoji uopste
        Category newCategory = new Category();
        newCategory.setCategoryName(normalizedName);
        newCategory = categoryRepository.save(newCategory);

        // povezi
        Agesex agesex = agesexRepository.findById(dto.getAgesexId())
                .orElseThrow(() -> new IllegalArgumentException("Agesex not found"));

        AgesexHasCategory newLink = new AgesexHasCategory();
        newLink.setAgesex(agesex);
        newLink.setCategory(newCategory);
        agesexHasCategoryRepository.save(newLink);
    }
}
