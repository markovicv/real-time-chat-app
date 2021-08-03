package com.example.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class MyAuthServiceException extends RuntimeException{

    private final HttpStatus httpStatus;

    public MyAuthServiceException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
