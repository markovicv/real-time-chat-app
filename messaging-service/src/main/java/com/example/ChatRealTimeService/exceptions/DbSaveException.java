package com.example.ChatRealTimeService.exceptions;

import org.springframework.http.HttpStatus;

public class DbSaveException extends MyAppException{

    public DbSaveException(String message, HttpStatus httpStatus){
        super(message,httpStatus);
    }
}
