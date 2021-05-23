package com.example.ChatRealTimeService.services.impl;

import com.example.ChatRealTimeService.model.domain.User;
import com.example.ChatRealTimeService.model.dto.UserDto;
import com.example.ChatRealTimeService.repo.UserRepository;
import com.example.ChatRealTimeService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override //TODO uraditi Auth registraciju !!
    public ResponseEntity<?> registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        return ResponseEntity.ok(userRepository.save(user));

    }
}
