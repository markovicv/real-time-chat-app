package com.example.authservice.service.impl;

import com.example.authservice.exceptions.PasswordMatchException;
import com.example.authservice.exceptions.UserException;
import com.example.authservice.mapper.UserJdbcMapper;
import com.example.authservice.model.domain.User;
import com.example.authservice.model.dtos.LoginDto;
import com.example.authservice.model.dtos.LoginResponseDto;
import com.example.authservice.model.dtos.RegisterDto;
import com.example.authservice.model.dtos.RegisterResponseDto;
import com.example.authservice.service.AuthService;
import com.example.authservice.service.TokenService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final JdbcTemplate jdbcTemplate;
    private final TokenService jwtTokenService;
    private final PasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(JdbcTemplate jdbcTemplate, TokenService tokenService, PasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.jwtTokenService = tokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        SecurityContextHolder.getContext().setAuthentication(validateCredentials(loginDto));
        return new LoginResponseDto(loginDto.getUsername(), jwtTokenService.createToken(loginDto.getUsername()),getUserByUsername(loginDto.getUsername()).getId());
    }

    private Authentication validateCredentials(LoginDto loginDto) {

        validateUserFields(loginDto.getUsername(),loginDto.getPassword());

        String queryUserByUsername = "SELECT * FROM chatService.user_table WHERE username=?;";

        try{
            User user = jdbcTemplate.queryForObject(queryUserByUsername, new UserJdbcMapper(),loginDto.getUsername());
            if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword()))
                throw new PasswordMatchException("Invalid password", HttpStatus.UNAUTHORIZED);

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

        }
        catch (Exception e){

            throw new UserException("user with that username not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private User getUserByUsername(String username){
        String queryUserByUsername = "SELECT * FROM chatService.user_table WHERE username=?;";

        return jdbcTemplate.queryForObject(queryUserByUsername, new UserJdbcMapper(),username);

    }

    @Override
    public RegisterResponseDto register(RegisterDto registerDto) {

        validateUserFields(registerDto.getUsername(),registerDto.getPassword());

        if(existsUser(registerDto.getUsername()))
            throw new UserException("user already exists",HttpStatus.FORBIDDEN);

            User registerUser = new User(registerDto.getUsername(),bCryptPasswordEncoder.encode(registerDto.getPassword()),null);
            jdbcTemplate.update("INSERT INTO chatService.user_table (username,password) VALUES (?,?)",registerUser.getUsername(),registerUser.getPassword());

        return new RegisterResponseDto("user registered!");


    }

    private void validateUserFields(String username,String password){
        if(Objects.isNull(username) || username.isEmpty())
            throw new UserException("username not provided",HttpStatus.NOT_FOUND);
        if(Objects.isNull(password) || password.isEmpty())
            throw new UserException("password not provided",HttpStatus.NOT_FOUND);


    }

    private boolean existsUser(String username){
        String queryUserByUsername = "SELECT COUNT(*) FROM chatService.user_table WHERE username = ?;";
        int exists = jdbcTemplate.queryForObject(queryUserByUsername,Integer.class,username);

        return exists>0;
    }
}
