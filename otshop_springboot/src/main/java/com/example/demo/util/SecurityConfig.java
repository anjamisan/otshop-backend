package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer{
	private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
	
	
	@Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

		http.csrf(csrf -> csrf.disable()) // Disable CSRF for testing
				.cors(Customizer.withDefaults()) // Enable CORS
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/test", "/api/auth/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
			    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

//        http.csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/**") //dodaj auth posle
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//    	    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
//    	      .permitAll())
//    	      .csrf(AbstractHttpConfigurer::disable);
//    	    

		//return http.build();
	}
	
	//prikazivanje slika na frontendu
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200")); // allow Angular
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true); // if you use cookies / session auth
//        //configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
//        configuration.setAllowedMethods(List.of("GET","POST"));
//        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        source.registerCorsConfiguration("/**",configuration);
//
//        return source;
//    }
}