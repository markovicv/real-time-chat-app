package com.example.authservice.model.dtos;

public class LoginResponseDto {

    private String username;
    private Long id;
    private String token;

    public LoginResponseDto(String username, String token,Long id) {
        this.username = username;
        this.token = token;
        this.id = id;
    }
    public LoginResponseDto(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }
}
