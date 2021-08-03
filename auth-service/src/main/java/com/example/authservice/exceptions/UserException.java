package com.example.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class UserException extends MyAuthServiceException{

    public UserException(String message, HttpStatus httpStatus){
        super(message,httpStatus);
    }
}
