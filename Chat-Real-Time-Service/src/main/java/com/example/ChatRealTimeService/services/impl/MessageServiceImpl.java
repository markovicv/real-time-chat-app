package com.example.ChatRealTimeService.services.impl;

import com.example.ChatRealTimeService.controller.MessageController;
import com.example.ChatRealTimeService.model.domain.Chat;
import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.domain.MessageStatus;
import com.example.ChatRealTimeService.model.domain.User;
import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.repo.ChatRepository;
import com.example.ChatRealTimeService.repo.MessageRepository;
import com.example.ChatRealTimeService.repo.UserRepository;
import com.example.ChatRealTimeService.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendMessage(MessageDto messageDto) {
        System.out.println("sio ti gej");
        boolean chatExists = chatRepository.existsById(messageDto.getChatId());
        if (!chatExists) {
            Chat savedChat = createChat(messageDto);
            Message message =  createMessage(savedChat,messageDto);
            simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getReceiverId()),"/queue/messages",message);
        }

        else{
            Chat chatRoom = chatRepository.findById(messageDto.getChatId()).orElseThrow(()->new RuntimeException("Error fetching chatRoom"));
//            if(roomBelongsToUsers(chatRoom,messageDto.getSenderId(),messageDto.getReceiverId())){
                Message message =  createMessage(chatRoom,messageDto);
                System.out.println(messageDto.getReceiverId());
                simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getReceiverId()),"/queue/messages",message);
//            }
        }



    }

    private boolean roomBelongsToUsers(Chat chatRoom,Long senderId,Long receiverId) {
        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();

        if(chatRoom.getUsers().contains(sender) && chatRoom.getUsers().contains(receiver))
            return true;
        return false;


    }

    @Override
    public ResponseEntity<?> getMessagesSavedForChat(Long chatId) {
        return ResponseEntity.ok(messageRepository.findMessageByChatIdAndMessageStatus(chatId,MessageStatus.SAVED));
    }

    @Override
    public ResponseEntity<?> getMessagesDeliveredForChat(Long chatId) {
        return ResponseEntity.ok(messageRepository.findMessageByChatIdAndMessageStatus(chatId,MessageStatus.DELIVERED));
    }

    @Override
    public ResponseEntity<?> getMessagesForChat(Long chatId) {
        return ResponseEntity.ok(messageRepository.findMessageByChatId(chatId));
    }

    @Transactional
    public Chat createChat(MessageDto messageDto){
        Chat chat = Chat.builder()
                .messageList(new ArrayList<>())
                .lastUpdate(System.currentTimeMillis())
                .users(new ArrayList<>())
                .build();
        chat.getUsers().add(userRepository.findById(messageDto.getSenderId()).get());
        chat.getUsers().add(userRepository.findById(messageDto.getReceiverId()).get());
        return chatRepository.save(chat);
    }

    @Transactional
    public Message createMessage(Chat chatRoom,MessageDto messageDto) {
        Message chatMessage = Message.builder()
                .chat(chatRoom)
                .messageStatus(MessageStatus.SAVED)
                .senderId(messageDto.getSenderId())
                .receiverId(messageDto.getReceiverId())
                .data(messageDto.getData())
                .build();
        return messageRepository.save(chatMessage);
    }
}
