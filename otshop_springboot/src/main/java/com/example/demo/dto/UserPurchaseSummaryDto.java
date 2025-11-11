package com.example.demo.dto;

import java.util.Date;

public class UserPurchaseSummaryDto {
    private String username;
    private String email;
    private int purchaseCount;
    private Date lastPurchaseDate;
    private int totalSpent;

    public UserPurchaseSummaryDto() {}

    public UserPurchaseSummaryDto(String username, String email, int purchaseCount, Date lastPurchaseDate, int totalSpent) {
        this.username = username;
        this.email = email;
        this.purchaseCount = purchaseCount;
        this.lastPurchaseDate = lastPurchaseDate;
        this.totalSpent = totalSpent;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getPurchaseCount() { return purchaseCount; }
    public void setPurchaseCount(int purchaseCount) { this.purchaseCount = purchaseCount; }

    public Date getLastPurchaseDate() { return lastPurchaseDate; }
    public void setLastPurchaseDate(Date lastPurchaseDate) { this.lastPurchaseDate = lastPurchaseDate; }

    public int getTotalSpent() { return totalSpent; }
    public void setTotalSpent(int totalSpent) { this.totalSpent = totalSpent; }
}
