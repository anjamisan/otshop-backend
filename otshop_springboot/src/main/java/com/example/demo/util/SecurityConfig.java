package com.example.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // Modern annotation
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; // New configuration class
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enables @PreAuthorize for method-level security
public class SecurityConfig {

    // --- BEANS: Core Security Components ---

    /**
     * Exposes the AuthenticationManager bean, required by the AuthController for login.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Defines the PasswordEncoder (BCrypt is standard and recommended).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the custom JWT Filter bean.
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        // Note: Autowired dependencies inside the filter will be handled by Spring
        return new JwtAuthenticationFilter();
    }

    // --- SECURITY FILTER CHAIN CONFIGURATION ---

    /**
     * Defines the modern SecurityFilterChain, replacing WebSecurityConfigurerAdapter.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Configure CORS and disable CSRF (required for stateless APIs)
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            
            // 2. Set Session Management to STATELESS (crucial for JWT)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // 3. Define Authorization Rules using Lambda DSL
            .authorizeHttpRequests(auth -> auth
                // Public Endpoints (accessible without a token)
                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll() 
                
                // All other /api/ routes MUST be authenticated (protected by JWT filter)
                .requestMatchers("/api/**").authenticated() 
                
                // Any other request (e.g., static files, favicon)
                .anyRequest().permitAll()
            )
            
            // 4. Integrate the JWT Filter into the security chain
            // It runs before the standard username/password filter
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
}