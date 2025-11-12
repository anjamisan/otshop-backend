package com.example.demo.services;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.OtshopSpringbootApplication;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.CustomUserDetails;

import model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final OtshopSpringbootApplication otshopSpringbootApplication;

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository,
			OtshopSpringbootApplication otshopSpringbootApplication) {
		this.userRepository = userRepository;
		this.otshopSpringbootApplication = otshopSpringbootApplication;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// ovaj metod se implicitno poziva iz AuthControllera prilikom autentifikacije
		User user = userRepository.findByUsername(usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

		return new CustomUserDetails(user);
	}

}