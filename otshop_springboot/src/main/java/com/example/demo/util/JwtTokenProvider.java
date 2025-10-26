package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
    @Value("${app.jwtSecret}") // Your secret key
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}") // Token validity time
    private int jwtExpirationMs;

    // Method to generate the token
    public String generateToken(Authentication authentication) {
        // Get the authenticated UserDetails (e.g., Spring Security's User object)
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 1. Define claims (data in the payload)
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        
        // 2. Build the token
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Subject is typically the user identifier
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8))) // Sign the token
                .compact();
    }
}