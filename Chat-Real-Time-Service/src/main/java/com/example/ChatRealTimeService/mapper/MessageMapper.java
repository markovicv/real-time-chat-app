package com.example.ChatRealTimeService.mapper;

import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.repo.ChatRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageMapper {

    private final ChatRepository chatRepository;


    public Message messageDtoToMessageDomain(MessageDto messageDto){
        return Message.builder()
                .id(messageDto.getId())
                .data(messageDto.getData())
                .senderId(messageDto.getSenderId())
                .receiverId(messageDto.getReceiverId())
                .chat(chatRepository.findById(messageDto.getChatId()).get())
                .build();
    }
}
