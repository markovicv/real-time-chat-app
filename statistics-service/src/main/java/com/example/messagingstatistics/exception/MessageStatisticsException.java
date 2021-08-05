package com.example.messagingstatistics.exception;

import org.springframework.http.HttpStatus;

public class MessageStatisticsException extends RuntimeException{

    private final HttpStatus httpStatus;

    public MessageStatisticsException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
