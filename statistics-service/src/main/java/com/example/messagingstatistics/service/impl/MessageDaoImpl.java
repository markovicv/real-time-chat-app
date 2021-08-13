package com.example.messagingstatistics.service.impl;

import com.example.messagingstatistics.model.dtos.MessageAmountReceivedPerFriendDto;
import com.example.messagingstatistics.model.dtos.MessageAmountSentPerFriendDto;
import com.example.messagingstatistics.model.mapper.MessageAmountReceivedPerFriendMapper;
import com.example.messagingstatistics.model.mapper.MessageAmountSentPerFriendMapper;
import com.example.messagingstatistics.service.MessageDao;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageDaoImpl implements MessageDao {


    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<MessageAmountSentPerFriendDto> getMessageAmountSendPerFriend(Long senderId) {
        String getMessageAmountSendPerFriendQuery = "SELECT UC.username, COUNT(receiver_id) as sentMessages FROM chatService.message JOIN chatService.user_table UC ON message.receiver_id=UC.id WHERE sender_id=? GROUP BY receiver_id;";
        return jdbcTemplate.query(getMessageAmountSendPerFriendQuery, new MessageAmountSentPerFriendMapper(),senderId);


    }

    @Override
    public List<MessageAmountReceivedPerFriendDto> getMessageAmountReceivedPerFriend(Long receiverId) {
        String getMessageAmountReceivedPerFriendQuery = "SELECT UC.username, COUNT(sender_id) as receivedMessages FROM chatService.message JOIN chatService.user_table UC ON message.sender_id=UC.id WHERE receiver_id=? GROUP BY UC.username;";
        return jdbcTemplate.query(getMessageAmountReceivedPerFriendQuery, new MessageAmountReceivedPerFriendMapper(),receiverId);


    }

    @Override
    public int getNumberOfMessagesSent(Long senderId) {
        String getNumberOfMessagesSentQuery = "SELECT count(sender_id) FROM chatService.message WHERE sender_id=?;";
        return jdbcTemplate.queryForObject(getNumberOfMessagesSentQuery, Integer.class,senderId);

    }

    @Override
    public int getNumberOfMessagesReceived(Long receiverId) {
        String getNumberOfMessagesReceivedQuery = "SELECT count(receiver_id) FROM chatService.message WHERE receiver_id=?;";
        return jdbcTemplate.queryForObject(getNumberOfMessagesReceivedQuery, Integer.class,receiverId);

    }
}
