package com.example.ChatRealTimeService.services.impl;

import com.example.ChatRealTimeService.exceptions.ChatException;
import com.example.ChatRealTimeService.exceptions.DbSaveException;
import com.example.ChatRealTimeService.exceptions.UserNotFoundException;
import com.example.ChatRealTimeService.mapper.MessageMapper;
import com.example.ChatRealTimeService.model.domain.Chat;
import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.domain.MessageStatus;
import com.example.ChatRealTimeService.model.domain.User;
import com.example.ChatRealTimeService.model.dto.ChatRoomMessagesDto;
import com.example.ChatRealTimeService.model.dto.Friend;
import com.example.ChatRealTimeService.model.dto.MessageDto;
import com.example.ChatRealTimeService.model.dto.MessageSeenDto;
import com.example.ChatRealTimeService.repo.ChatRepository;
import com.example.ChatRealTimeService.repo.MessageRepository;
import com.example.ChatRealTimeService.repo.UserRepository;
import com.example.ChatRealTimeService.services.ChatService;
import com.example.ChatRealTimeService.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageMapper messageMapper;
    private final ChatService chatService;


    @Override
    public void sendMessage(MessageDto message) {
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getReceiverId()), "/queue/messages", message);

    }

    @Override
    public MessageDto saveChatMessage(MessageDto messageDto) {
        long chatId = messageDto.getChatId();
        Chat chatRoom = chatRepository.findById(chatId).orElseThrow(() ->  new ChatException("Error fetching chatRoom",HttpStatus.NOT_FOUND));
        if (!roomBelongsToUsers(chatRoom, messageDto.getSenderId(), messageDto.getReceiverId()))
            throw new ChatException("User doesn't belong to this chat",HttpStatus.INTERNAL_SERVER_ERROR);

        Message message = createMessage(chatRoom,messageDto);

        return messageMapper.messageDomainToMessageDto(message);
    }

    private boolean roomBelongsToUsers(Chat chatRoom, Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId).orElseThrow(()->new UserNotFoundException("User not found ",HttpStatus.NOT_FOUND));
        User receiver = userRepository.findById(receiverId).orElseThrow(()->new UserNotFoundException("User not found ",HttpStatus.NOT_FOUND));

        return chatRoom.getUsers().contains(sender) && chatRoom.getUsers().contains(receiver);


    }

    @Override
    public List<Message> getMessagesSavedForChat(Long chatId) {
        return messageRepository.findMessageByChatIdAndMessageStatus(chatId, MessageStatus.SAVED);
    }

    @Override
    public List<Message> getMessagesDeliveredForChat(Long chatId) {
        return messageRepository.findMessageByChatIdAndMessageStatus(chatId, MessageStatus.DELIVERED);
    }

    @Override
    public MessageSeenDto markMessagesAsSeen(List<MessageDto> messageDtoList) {
        List<Message> seenMsgs = new ArrayList<>();

        for (MessageDto messageDto : messageDtoList) {
            Message message = messageMapper.messageDtoToMessageDomain(messageDto);
            message.setMessageStatus(MessageStatus.DELIVERED);
        }
        try {
            messageRepository.saveAll(seenMsgs);
            return new MessageSeenDto("Messages marked as seen");
        } catch (Exception e) {
            throw new DbSaveException("Error occurred while saving messages",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MessageSeenDto markOneMessageAsSeen(MessageDto messageDto) {
        Message message = messageMapper.messageDtoToMessageDomain(messageDto);
        message.setMessageStatus(MessageStatus.DELIVERED);
        try {
            messageRepository.save(message);
           return new MessageSeenDto("Messages marked as seen");
        } catch (Exception e) {
            throw new DbSaveException("Error occurred while saving message",HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //TODO morace da se promeni kada uvedem prijatelje,kao i id
    @Override
    public List<Friend> getAllFriends(Long myId) {
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

        return myFriends;

    }

    @Override
    public List<Message> getMessagesForChat(Long chatId) {
        return messageRepository.findMessageByChatId(chatId);
    }

    @Override
    public ChatRoomMessagesDto getAllMessagesBetweenTwoParties(Long senderId, Long receiverId) {
        Chat chat = chatService.findChatRoomBySenderAndReceiver(senderId,receiverId);

        List<Message> updateMessages = new ArrayList<>();
        List<Message> messagesBetweenTwoParties = messageRepository.findMessageByChatId(chat.getId());

        for(Message message : messagesBetweenTwoParties){
            if(message.getSenderId().equals(senderId) && message.getMessageStatus() == MessageStatus.SAVED){
                message.setMessageStatus(MessageStatus.DELIVERED);
                updateMessages.add(message);
            }
        }
        try{

            messageRepository.saveAll(updateMessages);

            List<MessageDto> messageDtoList =  messageRepository.findMessageByChatId(chat.getId()).stream().map(messageMapper::messageDomainToMessageDto)
                    .collect(Collectors.toList());
            return new ChatRoomMessagesDto(messageDtoList,chat.getId());
        }
        catch (Exception e){

            throw new DbSaveException("Error while saving messages between to users",HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
