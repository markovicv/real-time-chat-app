package com.example.messagingstatistics.service;

import org.springframework.http.ResponseEntity;

public interface MessageDao {

    ResponseEntity<?> getMessageAmountSendPerFriend();
    ResponseEntity<?> getMessageAmountReceivedPerFriend();
}
