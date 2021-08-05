package com.example.messagingstatistics.controller;


import com.example.messagingstatistics.model.dtos.MessageAmountReceivedPerFriendDto;
import com.example.messagingstatistics.model.dtos.MessageAmountSentPerFriendDto;
import com.example.messagingstatistics.service.MessageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})

public class MessageAnalyticsController {

    private final MessageDao messageDao;

    @GetMapping("/analytics/send/amount")
    public  List<MessageAmountSentPerFriendDto>  getMessagesAmountSendPerFriend(){
        return messageDao.getMessageAmountSendPerFriend();
    }

    @GetMapping("/analytics/received/amount")
    public List<MessageAmountReceivedPerFriendDto> getMessagesAmountReceivedPerFriend(){
        return messageDao.getMessageAmountReceivedPerFriend();
    }

    @GetMapping("/analytics/numberOfSentMessages")
    public int getNumberOfMessagesSent(){
        return messageDao.getNumberOfMessagesSent();
    }

    @GetMapping("/analytics/numberOfMessagesReceived")
    public int getNumberOfMessagesReceived(){
        return messageDao.getNumberOfMessagesReceived();
    }


}
