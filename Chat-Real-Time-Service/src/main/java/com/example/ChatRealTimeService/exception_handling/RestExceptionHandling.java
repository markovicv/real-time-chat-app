package com.example.ChatRealTimeService.exception_handling;


import com.example.ChatRealTimeService.exceptions.MyAppException;
import com.example.ChatRealTimeService.model.dto.ChatAppExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandling {


    @ExceptionHandler(value = {MyAppException.class})
    public ResponseEntity<ChatAppExceptionDto> handleException(MyAppException e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ChatAppExceptionDto(e.getMessage()));
    }

}
