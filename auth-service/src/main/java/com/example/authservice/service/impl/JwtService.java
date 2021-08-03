package com.example.authservice.service.impl;

import com.example.authservice.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService implements TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public String createToken(String username) {
        Claims claims =  Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validTo = new Date(now.getTime()+3600000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validTo)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
}
