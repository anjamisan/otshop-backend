package com.example.demo.dto;

public class AuthResponseDto {

    // 1. Fields
    private String jwtToken;
    private UserDto user;

    // 2. Default (No-Argument) Constructor
    // Required by many frameworks (like Jackson for JSON deserialization)
    public AuthResponseDto() {
    }

    // 3. Full Constructor (Recommended for creating the object in the Service layer)
    public AuthResponseDto(String jwtToken, UserDto user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    // 4. Getters
    
    public String getJwtToken() {
        return jwtToken;
    }

    public UserDto getUser() {
        return user;
    }

    // 5. Setters

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
