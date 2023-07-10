//package com.practical.PROJECT.auth;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        String customerId = obtainCustomerId(request);
//        String pin = obtainPin(request);
//
//        // Create an authentication token
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(customerId, pin);
//
//        // Authenticate the user
//        return getAuthenticationManager().authenticate(authRequest);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // Generate JWT token
//        String token = jwtTokenProvider.generateToken(authResult.getName());
//
//        // Add the token to the response headers
//        response.addHeader("Authorization", "Bearer " + token);
//    }
//}
