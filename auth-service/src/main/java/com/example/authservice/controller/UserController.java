package com.example.authservice.controller;

import com.example.authservice.model.dtos.LoginDto;
import com.example.authservice.model.dtos.LoginResponseDto;
import com.example.authservice.model.dtos.RegisterDto;
import com.example.authservice.model.dtos.RegisterResponseDto;
import com.example.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"*"})
public class UserController {


    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public RegisterResponseDto register(@RequestBody RegisterDto registerDto){
        return authService.register(registerDto);
    }

    @PostMapping("/auth/login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }




}
