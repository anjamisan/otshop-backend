package com.example.demo.dto;

import java.util.Date;

import model.Purchase;

public class PurchaseDto {

    private int idPurchase;
    private Date timestamp;
    private int productId;
    private String productName;

    
    private int userId;
    private String username;

    public PurchaseDto() {}

    public PurchaseDto(Purchase purchase) {
        this.idPurchase = purchase.getIdPurchase();
        this.timestamp = purchase.getTimestamp();

        if (purchase.getProduct() != null) {
            this.productId = purchase.getProduct().getIdProduct();
            this.productName = purchase.getProduct().getProductName();
        }

        if (purchase.getUser() != null) {
            this.userId = purchase.getUser().getIdUser();
            this.username = purchase.getUser().getUsername();
        }
    }

    
    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
