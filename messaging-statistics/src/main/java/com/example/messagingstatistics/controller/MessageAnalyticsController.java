package com.example.messagingstatistics.controller;


import com.example.messagingstatistics.service.MessageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})

public class MessageAnalyticsController {

    private final MessageDao messageDao;

    @GetMapping("/analytics/send/amount")
    public ResponseEntity<?> getMessagesAmountSendPerFriend(){
        return messageDao.getMessageAmountSendPerFriend();
    }

    @GetMapping("/analytics/received/amount")
    public ResponseEntity<?> getMessagesAmountReceivedPerFriend(){
        return messageDao.getMessageAmountReceivedPerFriend();
    }

    @GetMapping("/analytics/numberOfSentMessages")
    public ResponseEntity<?> getNumberOfMessagesSent(){
        return messageDao.getNumberOfMessagesSent();
    }

    @GetMapping("/analytics/numberOfMessagesReceived")
    public ResponseEntity<?> getNumberOfMessagesReceived(){
        return messageDao.getNumberOfMessagesReceived();
    }


}
