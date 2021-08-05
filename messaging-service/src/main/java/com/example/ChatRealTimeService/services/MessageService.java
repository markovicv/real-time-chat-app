package com.example.ChatRealTimeService.services;

import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.dto.ChatRoomMessagesDto;
import com.example.ChatRealTimeService.model.dto.Friend;
import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.model.dto.MessageSeenDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageDto message);
    List<Message> getMessagesSavedForChat(Long chatId);
    List<Message> getMessagesDeliveredForChat(Long chatId);
    List<Message> getMessagesForChat(Long chatId);
    MessageSeenDto markMessagesAsSeen(List<MessageDto> messageDtoList);
    MessageSeenDto markOneMessageAsSeen(MessageDto messageDto);
    List<Friend> getAllFriends(Long myId);
    ChatRoomMessagesDto getAllMessagesBetweenTwoParties(Long senderId, Long receiverId);
    MessageDto saveChatMessage(MessageDto messageDto);



}
