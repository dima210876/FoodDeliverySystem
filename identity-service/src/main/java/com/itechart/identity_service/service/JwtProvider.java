package com.itechart.identity_service.service;

import com.itechart.identity_service.model.JwtProperties;
import com.itechart.identity_service.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Jwts;

import javax.annotation.PostConstruct;

@ComponentScan
@RequiredArgsConstructor
public class JwtProvider {
    JwtProperties jwtProperties;

    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    public String createAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .claim("role", user.getRole())
                .setExpiration(calculateExpirationDate())
                .compact();
    }

    private Date calculateExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + jwtProperties.getValidityInMilliseconds());
    }

}
