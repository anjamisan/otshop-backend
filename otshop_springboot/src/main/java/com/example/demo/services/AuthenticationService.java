package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.RegisterUserDto;
import com.example.demo.repositories.UserRepository;

import model.User;

@Service
public class AuthenticationService {
	
	@Autowired
    UserRepository userRepository;
    
	@Autowired
    PasswordEncoder passwordEncoder;
    
	@Autowired
    AuthenticationManager authenticationManager;

   

    public User signup(RegisterUserDto input) {
        User user = new User();
        
		user.setEmail(input.getEmail());
		user.setUsername(input.getUsername());
		user.setAdmin(false); //PROMENI U FALSE NAKON UNOSA ADMINA
		user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginRequestDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsernameOrEmail(input.getUsername())
                .orElseThrow();
    }
}
