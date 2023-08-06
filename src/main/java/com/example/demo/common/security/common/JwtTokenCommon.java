package com.example.demo.common.security.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.common.security.model.UserPrincipal;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


public class JwtTokenCommon {
    @Value("${demo.app.jwtSecret}")
    private String jwtSecret;

    @Value("${demo.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${demo.app.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public String generateJwtToken(UserPrincipal userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername(),   userPrincipal.getId(), userPrincipal.getAuthorities());
    }

    public String generateJwtRefreshToken(UserPrincipal userPrincipal) {
        return generateRefreshTokenFromUsername(userPrincipal.getUsername());
    }

    public Algorithm algorithm() {
        return Algorithm.HMAC256(jwtSecret.getBytes());
    }

    public String generateTokenFromUsername(String username, Long userId, Collection<? extends GrantedAuthority> authorities) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .withIssuedAt(new Date())
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withClaim("user_id", userId)
                .sign(algorithm());
    }

    public String generateRefreshTokenFromUsername(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
                .withIssuedAt(new Date())
                .sign(algorithm());
    }
}
