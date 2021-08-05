package com.example.ChatRealTimeService.exceptions;

import org.springframework.http.HttpStatus;

public class MyAppException extends RuntimeException{

    private final HttpStatus httpStatus;

    public MyAppException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
