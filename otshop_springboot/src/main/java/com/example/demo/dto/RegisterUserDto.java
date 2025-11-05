package com.example.demo.dto;

public class RegisterUserDto {
	
    private String username;
    private String email;
    private String password;

    

	// 1. Full Constructor (Recommended for creating DTOs)
    public RegisterUserDto(String username, String password, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
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

}
