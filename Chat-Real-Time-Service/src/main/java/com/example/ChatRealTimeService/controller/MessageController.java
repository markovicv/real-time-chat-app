package com.example.ChatRealTimeService.controller;

import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
@CrossOrigin(origins = {"*"})
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/chat")
    public void sendMessage(@Payload MessageDto messageDto){
        System.out.println("okini ga");
         messageService.sendMessage(messageDto);
    }


    @GetMapping("/saved/chat/{chatId}")
    public ResponseEntity<?> getMessagesSavedForChat(@PathVariable("chatId") Long id){
        return messageService.getMessagesSavedForChat(id);
    }

    @GetMapping("/delivered/chat/{chatId}")
    public ResponseEntity<?> getMessagesDeliveredForChat(@PathVariable("chatId") Long id){
        return messageService.getMessagesDeliveredForChat(id);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?> getMessagesForChat(@PathVariable("chatId") Long id){
        return messageService.getMessagesForChat(id);
    }
}
