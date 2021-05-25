package com.example.ChatRealTimeService.repo;

import com.example.ChatRealTimeService.model.domain.Message;
import com.example.ChatRealTimeService.model.domain.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findMessageByChatId(Long chatId);

    List<Message> findMessageByChatIdAndMessageStatus(Long chatId, MessageStatus messageStatus);

    long countBySenderIdAndReceiverIdAndMessageStatus(Long senderId,Long receiverId,MessageStatus messageStatus);
}
