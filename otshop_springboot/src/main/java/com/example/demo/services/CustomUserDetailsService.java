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
import com.example.demo.util.CustomUserDetails;

import model.User;
import repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final OtshopSpringbootApplication otshopSpringbootApplication;

 private final UserRepository userRepository;

 public CustomUserDetailsService(UserRepository userRepository, OtshopSpringbootApplication otshopSpringbootApplication) {
     this.userRepository = userRepository;
     this.otshopSpringbootApplication = otshopSpringbootApplication;
 }

 @Override
 public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
     // ovaj metod se implicitno poziva iz AuthControllera prilikom autentifikacije
     User user = userRepository.findByUsernameOrEmail(usernameOrEmail)
             .orElseThrow(() -> 
                 new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

     // 2. Return a Spring Security UserDetails object
     //    (You might use Spring's User class or a custom class that implements UserDetails)
  // 2. Wrap the database entity into your custom UserDetails class
     return new CustomUserDetails(user);
 }
 
}