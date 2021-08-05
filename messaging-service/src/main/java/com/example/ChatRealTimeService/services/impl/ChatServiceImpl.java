package com.example.ChatRealTimeService.services.impl;

import com.example.ChatRealTimeService.model.domain.Chat;
import com.example.ChatRealTimeService.model.domain.User;
import com.example.ChatRealTimeService.repo.ChatRepository;
import com.example.ChatRealTimeService.repo.UserRepository;
import com.example.ChatRealTimeService.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;


    @Override
    public Chat findChatRoomBySenderAndReceiver(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId).orElseThrow(()->new RuntimeException("sender not found"));
        User receiver = userRepository.findById(receiverId).orElseThrow(()->new RuntimeException("receiver not found"));

        for(Chat chat:chatRepository.findAll()){
            List<User> userList = chat.getUsers();
            if(userList.contains(sender) && userList.contains(receiver))
                return chat;
        }
        return createNewChatRoom(sender,receiver);
    }

    private Chat createNewChatRoom(User sender,User receiver){
        Chat chat = Chat.builder()
                .messageList(new ArrayList<>())
                .lastUpdate(System.currentTimeMillis())
                .users(new ArrayList<>())
                .build();
        chat.getUsers().add(sender);
        chat.getUsers().add(receiver);

        return chatRepository.save(chat);
    }
}
