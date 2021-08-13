package com.example.messagingstatistics.controller;


import com.example.messagingstatistics.model.dtos.MessageAmountReceivedPerFriendDto;
import com.example.messagingstatistics.model.dtos.MessageAmountSentPerFriendDto;
import com.example.messagingstatistics.service.MessageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})

public class MessageAnalyticsController {

    private final MessageDao messageDao;

    @GetMapping("/analytics/send/amount/{id}")
    public  List<MessageAmountSentPerFriendDto>  getMessagesAmountSendPerFriend(@PathVariable("id") Long id){
        return messageDao.getMessageAmountSendPerFriend(id);
    }

    @GetMapping("/analytics/received/amount/{id}")
    public List<MessageAmountReceivedPerFriendDto> getMessagesAmountReceivedPerFriend(@PathVariable("id") Long id){
        return messageDao.getMessageAmountReceivedPerFriend(id);
    }

    @GetMapping("/analytics/numberOfSentMessages/{id}")
    public int getNumberOfMessagesSent(@PathVariable("id") Long id){
        return messageDao.getNumberOfMessagesSent(id);
    }

    @GetMapping("/analytics/numberOfMessagesReceived/{id}")
    public int getNumberOfMessagesReceived(@PathVariable("id")  Long id){
        return messageDao.getNumberOfMessagesReceived(id);
    }


}
