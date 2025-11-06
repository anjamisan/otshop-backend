package com.example.demo.controllers;


import model.Agesex;
import model.Category;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AgesexDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.services.AgesexCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/agesex")
public class AgesexCategoryController {

    private final AgesexCategoryService service;

    public AgesexCategoryController(AgesexCategoryService service) {
        this.service = service;
    }
    
    @GetMapping()
    public List<AgesexDto> getAgesex() {
        return service.getAgesex();
    }

    //prikazi dostupne kategorije za odabrani pol. ovo za admina kad unosi artikle
    @GetMapping("/{agesexId}/categories")
    public List<CategoryDto> getCategoriesForAgesex(@PathVariable int agesexId) {
        return service.getCategoriesForAgesex(agesexId);
    }
}
