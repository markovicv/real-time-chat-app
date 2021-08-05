package com.example.messagingstatistics.service;

import com.example.messagingstatistics.model.dtos.MessageAmountReceivedPerFriendDto;
import com.example.messagingstatistics.model.dtos.MessageAmountSentPerFriendDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageDao {

    List<MessageAmountSentPerFriendDto> getMessageAmountSendPerFriend();
    List<MessageAmountReceivedPerFriendDto> getMessageAmountReceivedPerFriend();
    int getNumberOfMessagesSent();
    int getNumberOfMessagesReceived();
}
