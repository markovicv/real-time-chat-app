package com.example.ChatRealTimeService.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private final String username;
    private final String password;

}
