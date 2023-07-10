//package com.practical.PROJECT.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//
//    private final AuthenticationManager authenticationManager;
////    private final JwtTokenProvider jwtTokenProvider;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public String authenticateAndGetToken(String customerId, String pin) {
//        try {
//            // Authenticate the user
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerId, pin));
//
//            // Generate and return JWT token
//            return jwtTokenProvider.generateToken(authentication.getName());
//        } catch (AuthenticationException e) {
//            // Handle authentication failure
//            throw new IllegalArgumentException("Invalid customer ID or PIN");
//        }
//    }
//
//    public String validateTokenAndGetCustomerId(String token) {
//        return jwtTokenProvider.validateTokenAndGetCustomerId(token);
//    }
//}
