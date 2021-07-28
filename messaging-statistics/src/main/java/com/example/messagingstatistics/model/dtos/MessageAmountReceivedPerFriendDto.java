package com.example.messagingstatistics.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageAmountReceivedPerFriendDto {


    private String username;
    private Long numberOfMessagesReceivedToFriend;

}
