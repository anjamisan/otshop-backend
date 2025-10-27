package com.example.demo.util;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenProvider {
    @Value("${app.jwtSecret}") // Your secret key
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}") // Token validity time
    private int jwtExpirationMs;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    
    private Key key() {
        // Uses the plain text secret and encodes it as UTF-8 bytes before generating the key
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

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
    
    public boolean validateToken(String authToken) {
        try {
            // Calls the new key() method for validation
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token: Malformed", ex); // Client bug or malformed string
        } catch (ExpiredJwtException ex) {
            logger.warn("Expired JWT token: {}", ex.getMessage()); // Normal operation (session end)
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token", ex); // Configuration issue
        } catch (IllegalArgumentException ex) {
            logger.warn("JWT claims string is empty or null", ex); // Client error
        } catch (SignatureException ex) {
            logger.error("JWT signature does not match secret key (TAMPERED!)", ex); // High security concern
        }
            return false;
        
    }
    
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                // Calls the new key() method for parsing
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}