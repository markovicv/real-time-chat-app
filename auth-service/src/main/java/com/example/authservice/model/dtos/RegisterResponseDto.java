package com.example.authservice.model.dtos;

public class RegisterResponseDto {

    private String message;

    public RegisterResponseDto(String message) {
        this.message = message;
    }
    public RegisterResponseDto(){

    }

    public String getMessage() {
        return message;
    }
}
