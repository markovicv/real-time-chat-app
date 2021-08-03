package com.example.authservice.exception_handler;

import com.example.authservice.exceptions.MyAuthServiceException;
import com.example.authservice.model.dtos.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandling {


    @ExceptionHandler(value = {MyAuthServiceException.class})
    public ResponseEntity<ErrorDto> handleException(MyAuthServiceException exception){
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new ErrorDto(exception.getMessage()));
    }

}
