package com.example.ChatRealTimeService.services;

import com.example.ChatRealTimeService.model.dto.MessageDto;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    ResponseEntity<?> sendMessage(MessageDto messageDto);
    ResponseEntity<?> getMessagesSavedForChat(Long chatId);
    ResponseEntity<?> getMessagesDeliveredForChat(Long chatId);
    ResponseEntity<?> getMessagesForChat(Long chatId);

}
