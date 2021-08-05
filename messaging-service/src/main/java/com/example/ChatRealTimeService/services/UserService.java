package com.example.ChatRealTimeService.services;

import com.example.ChatRealTimeService.model.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> registerUser(UserDto userDto);
    ResponseEntity<?> loginUser(UserDto userDto);
}
