package com.example.ChatRealTimeService.exceptions;


import org.springframework.http.HttpStatus;

public class UserNotFoundException extends MyAppException{

    public UserNotFoundException(String message, HttpStatus httpStatus){
        super(message,httpStatus);
    }


}
