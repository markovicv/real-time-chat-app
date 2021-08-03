package com.example.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class PasswordMatchException extends MyAuthServiceException{


    public PasswordMatchException(String message, HttpStatus httpStatus){
        super(message,httpStatus);

    }
}
