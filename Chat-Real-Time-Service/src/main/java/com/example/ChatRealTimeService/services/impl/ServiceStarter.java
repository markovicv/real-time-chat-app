package com.example.ChatRealTimeService.services.impl;

import com.example.ChatRealTimeService.model.domain.Chat;
import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.domain.User;
import com.example.ChatRealTimeService.repo.ChatRepository;
import com.example.ChatRealTimeService.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ServiceStarter implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Override
    public void run(String... args) throws Exception {

//        Chat chat = getChat();
//        try{
//            System.out.println(chat.getMessageList());
//
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        User user1 = new User();
//        user1.setUsername("vukasin");
//        User user2 = new User();
//        user2.setUsername("marko");
//
//        userRepository.save(user1);
//        userRepository.save(user2);
    }

    @Transactional
    public Chat getChat(){
        return chatRepository.findById((long)1).get();
    }
}
