package com.example.authservice.service.impl;

import com.example.authservice.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService implements TokenService {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    void init(){

        this.key = Keys.hmacShaKeyFor(secret.getBytes());
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
                .signWith(key)
                .compact();
    }
}
