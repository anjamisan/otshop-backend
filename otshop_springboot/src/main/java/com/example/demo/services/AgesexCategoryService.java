package com.example.demo.services;

import model.Agesex;
import model.Category;
import model.AgesexHasCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.AgesexHasCategoryRepository;
import com.example.demo.repositories.AgesexRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgesexCategoryService {

	@Autowired
    AgesexRepository agesexRepository;
	@Autowired
    AgesexHasCategoryRepository agesexHasCategoryRepository;

   

    public List<Category> getCategoriesForAgesex(int agesexId) {
        Agesex agesex = agesexRepository.findById(agesexId)
                .orElseThrow(() -> new IllegalArgumentException("Agesex not found"));

        List<AgesexHasCategory> pairs = agesexHasCategoryRepository.findByAgesex(agesex);

        return pairs.stream()
                .map(AgesexHasCategory::getCategory)
                .collect(Collectors.toList());
    }
}

