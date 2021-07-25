package com.example.ChatRealTimeService.controller;

import com.example.ChatRealTimeService.model.dto.PublicChatMessageDto;
import com.example.ChatRealTimeService.services.PublicChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class PublicMessageController {

    private final PublicChatRoomService publicChatRoomService;

    @MessageMapping("/publicChat")
    public void sendMessageToPublicChatRoom(@Payload PublicChatMessageDto publicChatMessageDto){
        System.out.println("AAAALLLLLOOOO");
        publicChatRoomService.sendMessageToPublicChatRoom(publicChatMessageDto);
    }
}
