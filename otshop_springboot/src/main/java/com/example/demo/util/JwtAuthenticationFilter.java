package com.example.demo.util;

import com.example.demo.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

 @Autowired
 private JwtTokenProvider tokenProvider;
 
 @Autowired
 private CustomUserDetailsService customUserDetailsService;

 @Override
 protected void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
     try {
         String jwt = getJwtFromRequest(request);

         // 1. Check if token is present and valid
         if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
             
             // 2. Extract username from token
             String username = tokenProvider.getUsernameFromJWT(jwt);

             // 3. Load user details (implicitly calls loadUserByUsername)
             UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

             // 4. Create new Authentication object
             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                     userDetails, null, userDetails.getAuthorities());
             
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             // 5. Set Authentication in Security Context
             //    This makes the user officially authenticated for this request.
             SecurityContextHolder.getContext().setAuthentication(authentication);
         }
     } catch (Exception ex) {
         logger.error("Could not set user authentication in security context", ex);
     }

     filterChain.doFilter(request, response);
 }

 // Helper method to extract the JWT from the Authorization header
 private String getJwtFromRequest(HttpServletRequest request) {
     String bearerToken = request.getHeader("Authorization");
     
     if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
         return bearerToken.substring(7);
     }
     return null;
 }
}
