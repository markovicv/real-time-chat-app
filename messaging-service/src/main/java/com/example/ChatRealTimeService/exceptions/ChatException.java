package com.example.ChatRealTimeService.exceptions;

import org.springframework.http.HttpStatus;

public class ChatException extends MyAppException{

    public ChatException(String message, HttpStatus httpStatus){
        super(message,httpStatus);
    }
}
