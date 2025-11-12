package com.example.demo.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.RegisterUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserService;
import com.example.demo.util.CustomUserDetails;

import model.User;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	@Autowired
    JwtService jwtService;
    
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	UserService userService;


	
	@PostMapping("/signup")
	public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto registerUserDto) { // User
		User registeredUser = authenticationService.signup(registerUserDto); // // return
		
		UserDto userDto = new UserDto(
		        registeredUser.getIdUser(),
		        registeredUser.getUsername(),
		        registeredUser.getEmail(),
		        registeredUser.isAdmin()
		    );
		return ResponseEntity.ok(userDto);
	}

	@PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody LoginRequestDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(new CustomUserDetails(authenticatedUser));

        AuthResponseDto loginResponse = new AuthResponseDto(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
	
	

	@GetMapping("/profile")
	public ResponseEntity<UserDto> profile(@AuthenticationPrincipal CustomUserDetails userDetails) {
	    User user = userService.findByUsername(userDetails.getUsername());
	    return ResponseEntity.ok(new UserDto(user.getIdUser(), user.getUsername(), user.getEmail(), user.isAdmin()));
	}

}