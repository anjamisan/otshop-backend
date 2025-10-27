package com.example.demo.controllers;



import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.util.CustomUserDetails;
import com.example.demo.util.JwtTokenProvider;

import model.User;

import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    // --- Login Endpoint Implementation ---

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticateUser(@RequestBody LoginRequestDto loginRequest) {

        try {
            // 1. Attempt Authentication: Creates a pre-authentication token 
            //    and delegates verification (UserDetailsService + PasswordEncoder)
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            // 2. Set Context: Stores the fully authenticated object for this request's lifetime
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3. Generate JWT
            String jwt = tokenProvider.generateToken(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User userEntity = userDetails.getUserEntity(); 
            
            // 🔑 5. CONSTRUCT DTO: Use the retrieved User entity to create the UserDto
            // Assuming you have a UserDto constructor that takes a User entity.
            UserDto userDto = new UserDto(userEntity.getIdUser(), userEntity.getUsername(), userEntity.getEmail(), userEntity.isAdmin());
            
            AuthResponseDto response = new AuthResponseDto(jwt, userDto);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            // Handles exceptions thrown by authenticationManager (e.g., BadCredentialsException, UsernameNotFoundException).
            // This ensures a controlled 401 Unauthorized response instead of letting the exception bubble up.
            System.err.println("Authentication failed for user " + loginRequest.getUsername() + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getAuthenticatedUserProfile(
        @AuthenticationPrincipal CustomUserDetails principal
    ) {
        // We already know the user is authenticated and the token is valid,
        // because the JWT filter set the principal.
        
        // 1. Get the underlying User entity
        User userEntity = principal.getUserEntity();

        // 2. Fetch the most current DTO data (optional: use service for complex fetch)
        // Using a direct DTO constructor for simplicity here:
        UserDto userProfile = new UserDto(userEntity.getIdUser(), userEntity.getUsername(), userEntity.getEmail(), userEntity.isAdmin());

        return ResponseEntity.ok(userProfile);
    }
}
