package com.example.messagingstatistics.service;

import org.springframework.http.ResponseEntity;

public interface MessageAnalyticsService {
    ResponseEntity<?> getMessageAmountSendPerFriend();
    ResponseEntity<?> getMessageAmountReceivedPerFriend();
}
