package com.example.ChatRealTimeService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Long id;
    private Long chatId;
    private String data;
    private Long senderId;
    private Long receiverId;
    private Long createdTimeInMillis;
}
