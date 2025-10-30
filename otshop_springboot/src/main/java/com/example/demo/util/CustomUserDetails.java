package com.example.demo.util;

//File: com.example.security.CustomUserDetails.java

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final User user;

	public CustomUserDetails(User user) {
		this.user = user;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = (user.isAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// --- Account Status Methods (Often hardcoded to true for basic JWT setup) ---

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Assuming user is always active if loaded from DB
		return true;
	}

	// potrebno za generisanje DTO-a
	public User getUserEntity() {
		return user;
	}
}
