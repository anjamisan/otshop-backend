package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;
import model.Product;
import model.Productimage;

public class ProductDto {

    private int idProduct;
    private String productName;
    private String description;
    private String condition; // from enum ConditionType
    private int price;

    // related info (flattened)
    private String categoryName;
    private String ageSexGroup;

    // image URLs (from Productimage)
    private List<String> imageUrls;
    
    private boolean sold;

    // Constructors
    public ProductDto() {}

    // convenient conversion constructor
    public ProductDto(Product product) {
        this.idProduct = product.getIdProduct();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.condition = product.getCondition() != null ? product.getCondition().name() : null;
        this.price = product.getPrice();

        if (product.getCategory() != null)
            this.categoryName = product.getCategory().getCategoryName();

        if (product.getAgesex() != null)
            this.ageSexGroup = product.getAgesex().getAgeSexGroup();

        if (product.getProductimages() != null) {
            this.imageUrls = product.getProductimages().stream()
                    .map(Productimage::getUrl)
                    .collect(Collectors.toList());
        }
        this.sold = product.getPurchases() != null;
    }

    // Getters and setters
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAgeSexGroup() {
        return ageSexGroup;
    }

    public void setAgeSexGroup(String ageSexGroup) {
        this.ageSexGroup = ageSexGroup;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
    
    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
