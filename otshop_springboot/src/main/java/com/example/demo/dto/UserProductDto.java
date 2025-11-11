package com.example.demo.dto;

import model.Savedproduct;

public class UserProductDto {
    private int userId;
    private int productId;

    public UserProductDto() {}

    public UserProductDto(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }
    
    public UserProductDto(Savedproduct p) {
    	this.userId = p.getUser().getIdUser();
    	this.productId = p.getProduct().getIdProduct();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
