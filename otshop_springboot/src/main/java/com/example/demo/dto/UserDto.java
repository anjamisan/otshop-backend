package com.example.demo.dto;

public class UserDto {
    
    private int idUser;
    private String username;
    private String email;
    private boolean isAdmin; // Mapping byte jeAdmin to boolean

    // 1. Full Constructor (Recommended for creating DTOs)
    public UserDto(int idUser, String username, String email, boolean isAdmin) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    // 2. Default (No-Argument) Constructor (Often required by frameworks like Spring/Jackson)
    public UserDto() {
    }

    // 3. Getters
    
    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    // Custom getter for boolean field, often named 'is...' 
    public boolean isAdmin() {
        return isAdmin;
    }

    // 4. Setters

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
