// com.example.demo.dto.AddCategoryDto.java
package com.example.demo.dto;

public class AddCategoryDto {
    private String categoryName;
    private int agesexId;

    public AddCategoryDto() {}

    public AddCategoryDto(String categoryName, int agesexId) {
        this.categoryName = categoryName;
        this.agesexId = agesexId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getAgesexId() {
        return agesexId;
    }

    public void setAgesexId(int agesexId) {
        this.agesexId = agesexId;
    }
}
