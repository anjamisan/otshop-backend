package com.example.demo.dto;

public class UserSummaryDto {
    private int idUser;
    private String username;
    private String email;
    private long purchaseCount;
    private boolean admin;

    public UserSummaryDto(int idUser, String username, String email, boolean admin, long purchaseCount) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.purchaseCount = purchaseCount;
        this.admin = admin;
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

	public boolean isAdmin() {
		return admin;
	}
    
    
}
