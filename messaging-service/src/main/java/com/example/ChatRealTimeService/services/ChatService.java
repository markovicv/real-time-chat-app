package com.example.ChatRealTimeService.services;

import com.example.ChatRealTimeService.model.domain.Chat;

public interface ChatService {

    Chat findChatRoomBySenderAndReceiver(Long senderId,Long receiverId);

}
