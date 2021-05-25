package com.example.ChatRealTimeService.services;

import com.example.ChatRealTimeService.model.dto.MessageDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageDto messageDto);
    ResponseEntity<?> getMessagesSavedForChat(Long chatId);
    ResponseEntity<?> getMessagesDeliveredForChat(Long chatId);
    ResponseEntity<?> getMessagesForChat(Long chatId);
    ResponseEntity<?> markMessagesAsSeen(List<MessageDto> messageDtoList);
    ResponseEntity<?> markOneMessageAsSeen(MessageDto messageDto);
    ResponseEntity<?> getAllFriends(Long myId);

}
