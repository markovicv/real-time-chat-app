package com.example.messagingstatistics.service.impl;

import com.example.messagingstatistics.model.mapper.MessageAmountReceivedPerFriendMapper;
import com.example.messagingstatistics.model.mapper.MessageAmountSentPerFriendMapper;
import com.example.messagingstatistics.service.MessageDao;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageDaoImpl implements MessageDao {



    private final JdbcTemplate jdbcTemplate;


    @Override
    public ResponseEntity<?> getMessageAmountSendPerFriend() {
        String getMessageAmountSendPerFriendQuery = "SELECT UC.username, COUNT(receiver_id) as sentMessages FROM chatService.message JOIN chatService.user_table UC ON message.receiver_id=UC.id WHERE sender_id=1 GROUP BY receiver_id;";
        return ResponseEntity.ok(jdbcTemplate.query(getMessageAmountSendPerFriendQuery,new MessageAmountSentPerFriendMapper()));




    }

    @Override
    public ResponseEntity<?> getMessageAmountReceivedPerFriend() {
        String getMessageAmountReceivedPerFriendQuery = "SELECT UC.username, COUNT(sender_id) as receivedMessages FROM chatService.message JOIN chatService.user_table UC ON message.sender_id=UC.id WHERE receiver_id =1 GROUP BY UC.username;";
        return ResponseEntity.ok(jdbcTemplate.query(getMessageAmountReceivedPerFriendQuery,new MessageAmountReceivedPerFriendMapper()));
    }
}
