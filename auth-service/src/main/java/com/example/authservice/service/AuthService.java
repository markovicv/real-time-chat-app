package com.example.authservice.service;

import com.example.authservice.model.dtos.LoginDto;
import com.example.authservice.model.dtos.LoginResponseDto;
import com.example.authservice.model.dtos.RegisterDto;
import com.example.authservice.model.dtos.RegisterResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginDto loginDto);
    RegisterResponseDto register(RegisterDto registerDto);


}
