package com.example.messagingstatistics.exceptionHandling;


import com.example.messagingstatistics.exception.MessageStatisticsException;
import com.example.messagingstatistics.model.dtos.StatisticsExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MessageStatisticsExceptionHandler {


    @ExceptionHandler(value = {MessageStatisticsException.class})
    @ResponseBody
    public ResponseEntity<StatisticsExceptionDto> handleException(MessageStatisticsException messageStatisticsException){
        return ResponseEntity
                .status(messageStatisticsException.getHttpStatus())
                .body(new StatisticsExceptionDto(messageStatisticsException.getMessage()));
    }

}
