package com.example.demo.dto;

public class AuthResponseDto {

    // 1. Fields
    private String jwtToken;
    private long expiresIn;

    // 2. Default (No-Argument) Constructor
    // Required by many frameworks (like Jackson for JSON deserialization)
    public AuthResponseDto() {
    }

    public AuthResponseDto(String jwtToken, long expiresIn) {
		super();
		this.jwtToken = jwtToken;
		this.expiresIn = expiresIn;
	}
 
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	
}
