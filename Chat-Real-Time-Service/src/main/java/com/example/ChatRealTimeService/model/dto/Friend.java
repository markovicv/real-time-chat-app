package com.example.ChatRealTimeService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    private String username;
    private Long friendId;
    private long numberOfUnreadMessages;


}
