package com.example.demo.dto;

import java.util.List;

public class ProductResponseDto {
    private Integer idProduct;
    private String productName;
    private String condition;
    private String description;
    private Integer price;
    private Integer agesexId;
    private Integer categoryId;
    private List<String> imageUrls; // optional
    
	public ProductResponseDto() {
		
	}
	public ProductResponseDto(Integer idProduct, String productName, String condition, String description,
			Integer price, Integer agesexId, Integer categoryId, List<String> imageUrls) {
		super();
		this.idProduct = idProduct;
		this.productName = productName;
		this.condition = condition;
		this.description = description;
		this.price = price;
		this.agesexId = agesexId;
		this.categoryId = categoryId;
		this.imageUrls = imageUrls;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
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
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

    
}
