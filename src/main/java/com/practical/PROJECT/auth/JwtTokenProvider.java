//package com.practical.PROJECT.auth;
//
//import com.practical.PROJECT.config.JwtConfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    private final JwtConfig jwtConfig;
//
//    @Autowired
//    public JwtTokenProvider(JwtConfig jwtConfig) {
//        this.jwtConfig = jwtConfig;
//    }
//
//    public String generateToken(String customerId) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtConfig.getExpiration() * 1000L);
//
//        return Jwts.builder()
//                .setSubject(customerId)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
//                .compact();
//    }
//
//    public String validateTokenAndGetCustomerId(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtConfig.getSecret())
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.getSubject();
//    }
//}
