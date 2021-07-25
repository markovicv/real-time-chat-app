package com.example.ChatRealTimeService.services.impl;

import com.example.ChatRealTimeService.model.domain.User;
import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.model.dto.PublicChatMessageDto;
import com.example.ChatRealTimeService.repo.UserRepository;
import com.example.ChatRealTimeService.services.PublicChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicChatRoomServiceImpl implements PublicChatRoomService {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;

    @Override
    public void sendMessageToPublicChatRoom(PublicChatMessageDto publicChatMessageDto) {
        User user = userRepository.findById(publicChatMessageDto.getSenderId()).orElseThrow(()->new RuntimeException("user doenst exist"));
        System.out.println(user.getUsername());
        publicChatMessageDto.setSenderName(user.getUsername());
        simpMessagingTemplate.convertAndSend("/chatRoom",publicChatMessageDto);
    }
}
