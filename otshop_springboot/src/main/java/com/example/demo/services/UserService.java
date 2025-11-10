package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserSummaryDto;
import com.example.demo.repositories.UserRepository;

import model.User;

@Service
public class UserService {
	
	@Autowired
    UserRepository userRepository;

    public User findByUsername(String username) {
        // We throw RuntimeException here because the user should have been found by 
        // CustomUserDetailsService if we reached this point, but fail-fast is safe.
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
    
    public List<UserSummaryDto> getAllUserSummaries() {
        return userRepository.findAllWithPurchaseCount();
    }
}
