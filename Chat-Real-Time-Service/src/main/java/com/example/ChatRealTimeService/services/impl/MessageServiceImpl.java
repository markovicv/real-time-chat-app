package com.example.ChatRealTimeService.services.impl;

import com.example.ChatRealTimeService.controller.MessageController;
import com.example.ChatRealTimeService.mapper.MessageMapper;
import com.example.ChatRealTimeService.model.domain.Chat;
import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.domain.MessageStatus;
import com.example.ChatRealTimeService.model.domain.User;
import com.example.ChatRealTimeService.model.dto.Friend;
import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.model.dto.UserDto;
import com.example.ChatRealTimeService.repo.ChatRepository;
import com.example.ChatRealTimeService.repo.MessageRepository;
import com.example.ChatRealTimeService.repo.UserRepository;
import com.example.ChatRealTimeService.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageMapper messageMapper;

    @Override
    public void sendMessage(MessageDto messageDto) {

        boolean chatExists = chatRepository.existsById(messageDto.getChatId());
        long chatId = messageDto.getChatId();
        if (!chatExists) {
            Chat savedChat = createChat(messageDto);
            chatId = savedChat.getId();
        }
        Chat chatRoom = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Error fetching chatRoom"));
        if (!roomBelongsToUsers(chatRoom, messageDto.getSenderId(), messageDto.getReceiverId()))
            throw new RuntimeException("users dont belong to chat");

        Message message = createMessage(chatRoom, messageDto);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getReceiverId()), "/queue/messages", message);


    }

    private boolean roomBelongsToUsers(Chat chatRoom, Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();

        if (chatRoom.getUsers().contains(sender) && chatRoom.getUsers().contains(receiver))
            return true;
        return false;


    }

    @Override
    public ResponseEntity<?> getMessagesSavedForChat(Long chatId) {
        return ResponseEntity.ok(messageRepository.findMessageByChatIdAndMessageStatus(chatId, MessageStatus.SAVED));
    }

    @Override
    public ResponseEntity<?> getMessagesDeliveredForChat(Long chatId) {
        return ResponseEntity.ok(messageRepository.findMessageByChatIdAndMessageStatus(chatId, MessageStatus.DELIVERED));
    }

    @Override
    public ResponseEntity<?> markMessagesAsSeen(List<MessageDto> messageDtoList) {
        List<Message> seenMsgs = new ArrayList<>();

        for (MessageDto messageDto : messageDtoList) {
            Message message = messageMapper.messageDtoToMessageDomain(messageDto);
            message.setMessageStatus(MessageStatus.DELIVERED);
        }
        try {
            messageRepository.saveAll(seenMsgs);
            return ResponseEntity.ok("Messages marked as seen");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error marking messages as seen");
        }
    }

    @Override
    public ResponseEntity<?> markOneMessageAsSeen(MessageDto messageDto) {
        Message message = messageMapper.messageDtoToMessageDomain(messageDto);
        message.setMessageStatus(MessageStatus.DELIVERED);
        try {
            messageRepository.save(message);
            return ResponseEntity.ok("Messages marked as seen");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error marking messages as seen");
        }
    }

    //TODO morace da se promeni kada uvedem prijatelje,kao i id
    @Override
    public ResponseEntity<?> getAllFriends(Long myId) {
        List<User> friends = userRepository.findAll();
        List<Friend> myFriends = new ArrayList<>();

        for(User user:friends){
            if(user.getId().equals(myId))
                continue;
            Friend friend = new Friend();
            friend.setFriendId(user.getId());
            friend.setUsername(user.getUsername());
            friend.setNumberOfUnreadMessages(
                    messageRepository.countBySenderIdAndReceiverIdAndMessageStatus(user.getId(),myId,MessageStatus.SAVED)
            );
            myFriends.add(friend);
        }

        return ResponseEntity.ok(myFriends);

    }

    @Override
    public ResponseEntity<?> getMessagesForChat(Long chatId) {
        return ResponseEntity.ok(messageRepository.findMessageByChatId(chatId));
    }

    @Transactional
    public Chat createChat(MessageDto messageDto) {
        Chat chat = Chat.builder()
                .messageList(new ArrayList<>())
                .lastUpdate(System.currentTimeMillis())
                .users(new ArrayList<>())
                .build();
        chat.getUsers().add(userRepository.findById(messageDto.getSenderId()).get());
        chat.getUsers().add(userRepository.findById(messageDto.getReceiverId()).get());
        return chatRepository.save(chat);
    }

    @Transactional
    public Message createMessage(Chat chatRoom, MessageDto messageDto) {
        Message chatMessage = Message.builder()
                .chat(chatRoom)
                .messageStatus(MessageStatus.SAVED)
                .senderId(messageDto.getSenderId())
                .receiverId(messageDto.getReceiverId())
                .data(messageDto.getData())
                .build();
        return messageRepository.save(chatMessage);
    }
}
