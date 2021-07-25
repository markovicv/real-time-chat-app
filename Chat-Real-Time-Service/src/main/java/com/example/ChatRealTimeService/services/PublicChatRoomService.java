package com.example.ChatRealTimeService.services;

import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.model.dto.PublicChatMessageDto;

public interface PublicChatRoomService {


    void sendMessageToPublicChatRoom(PublicChatMessageDto publicChatMessageDto);
}
