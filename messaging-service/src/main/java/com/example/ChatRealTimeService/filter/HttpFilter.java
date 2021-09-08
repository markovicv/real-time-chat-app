package com.example.ChatRealTimeService.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;

@Component
public class HttpFilter extends OncePerRequestFilter {
    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String bearer = httpServletRequest.getHeader("Authorization");
        String username = getUsernameFromToken(bearer.substring(7));

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username,null));

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());




        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }


    private String getUsernameFromToken(String jwtToken){
       return Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }
}
