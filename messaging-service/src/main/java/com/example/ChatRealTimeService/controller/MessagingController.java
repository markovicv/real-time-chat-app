package com.example.ChatRealTimeService.controller;

import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.dto.ChatRoomMessagesDto;
import com.example.ChatRealTimeService.model.dto.Friend;
import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/message")
public class MessagingController {

    private final MessageService messageService;

    @MessageMapping("/chat")
    public void sendMessage(@Payload MessageDto message){
         messageService.sendMessage(message);
    }

    @PostMapping("/chat")
    public MessageDto saveChatMessage(@RequestBody MessageDto messageDto){
        return messageService.saveChatMessage(messageDto);
    }

    @GetMapping("/saved/chat/{chatId}")
    public List<Message> getMessagesSavedForChat(@PathVariable("chatId") Long id){
        return messageService.getMessagesSavedForChat(id);
    }

    @GetMapping("/delivered/chat/{chatId}")
    public List<Message> getMessagesDeliveredForChat(@PathVariable("chatId") Long id){
        return messageService.getMessagesDeliveredForChat(id);
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> getMessagesForChat(@PathVariable("chatId") Long id){
        return messageService.getMessagesForChat(id);
    }

    @GetMapping("/friends/{id}")
    public List<Friend> getAllFriends(@PathVariable("id") Long id){
        return messageService.getAllFriends(id);
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ChatRoomMessagesDto getMessagesBetweenTwoParties(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId){
        return messageService.getAllMessagesBetweenTwoParties(senderId,receiverId);
    }

    @GetMapping("/test")
    public String test(){
        return "AAAAAAAAAAAAAAAAAAAAAAAAAA";
    }


}
