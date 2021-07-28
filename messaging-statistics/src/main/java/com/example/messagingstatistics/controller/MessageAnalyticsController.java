package com.example.messagingstatistics.controller;


import com.example.messagingstatistics.service.MessageAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageAnalyticsController {

    private final MessageAnalyticsService messageAnalyticsService;

    @GetMapping("/analytics/send/amount")
    public ResponseEntity<?> getMessageAmountSendPerFriend(){
        return messageAnalyticsService.getMessageAmountSendPerFriend();
    }


}
