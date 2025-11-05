package com.example.demo.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductCreateDto {
    @NotBlank
    private String productName;

    private String condition;
    private String description;

    
    @NotNull
    @Min(0)
    private Integer price;

    @NotNull
    private Integer agesexId;

    @NotNull
    private Integer categoryId;

    private List<MultipartFile> images;
    
	public ProductCreateDto() {
	}

	public ProductCreateDto(@NotBlank String productName, String condition, String description,
			@NotNull @Min(0) Integer price, @NotNull Integer agesexId, @NotNull Integer categoryId,
			List<MultipartFile> imageUrls) {
		super();
		this.productName = productName;
		this.condition = condition;
		this.description = description;
		this.price = price;
		this.agesexId = agesexId;
		this.categoryId = categoryId;
		this.images = imageUrls;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAgesexId() {
		return agesexId;
	}

	public void setAgesexId(Integer agesexId) {
		this.agesexId = agesexId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> imageUrls) {
		this.images = imageUrls;
	}

    
}


