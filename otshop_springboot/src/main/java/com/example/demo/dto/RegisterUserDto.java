package com.example.demo.dto;

public class RegisterUserDto {
	
    private String username;
    private String email;
    private boolean isAdmin; 
    private String password;

    

	// 1. Full Constructor (Recommended for creating DTOs)
    public RegisterUserDto(String username, String password, String email, boolean isAdmin) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    // 2. Default (No-Argument) Constructor (Often required by frameworks like Spring/Jackson)
    public RegisterUserDto() {
    }
 

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
