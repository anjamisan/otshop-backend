package com.example.demo.dto;

import model.Category;

public class CategoryDto {
	
	private int idCategory;

	private String categoryName;

	public CategoryDto() {
	
	}

	public CategoryDto(int idCategory, String categoryName) {
		super();
		this.idCategory = idCategory;
		this.categoryName = categoryName;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public static CategoryDto fromEntity(Category category) {
        if (category == null) return null;

        return new CategoryDto(
            category.getIdCategory(),
            category.getCategoryName()
        );
    }

}
