package com.example.authservice.model.domain;

public class User {

    private  String username;
    private  String password;
    private Long id;

    public User(String username, String password,Long id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
