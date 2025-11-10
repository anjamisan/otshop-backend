package com.example.demo.dto;

public class UserSummaryDto {
    private int idUser;
    private String username;
    private String email;
    private long purchaseCount;

    public UserSummaryDto(int idUser, String username, String email, long purchaseCount) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.purchaseCount = purchaseCount;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public long getPurchaseCount() {
        return purchaseCount;
    }
}
