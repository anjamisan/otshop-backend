package com.example.demo.dto;

public class LoginRequestDto {

    // 1. Fields
    // These names must match the keys in the JSON payload sent by Angular
    private String username;
    private String password; // Corresponds to the 'sifra' field in your User entity, but is labeled 'password' for standard security practices

    // 2. Default (No-Argument) Constructor
    // Required by many frameworks (like Jackson for JSON deserialization)
    public LoginRequestDto() {
    }

    // 3. Full Constructor
    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 4. Getters
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // 5. Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
