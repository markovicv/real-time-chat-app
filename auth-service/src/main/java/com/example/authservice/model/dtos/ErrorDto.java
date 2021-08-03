package com.example.authservice.model.dtos;

public class ErrorDto {

    private String message;

    public ErrorDto(String message){
        this.message = message;
    }

    public ErrorDto(){

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
