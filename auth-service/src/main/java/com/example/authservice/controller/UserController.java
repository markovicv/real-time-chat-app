package com.example.authservice.controller;

import com.example.authservice.model.dtos.LoginDto;
import com.example.authservice.model.dtos.LoginResponseDto;
import com.example.authservice.model.dtos.RegisterDto;
import com.example.authservice.model.dtos.RegisterResponseDto;
import com.example.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/auth")
public class UserController {


    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponseDto register(@RequestBody RegisterDto registerDto){
        return authService.register(registerDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }




}
