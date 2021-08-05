package com.example.messagingstatistics.model.mapper;

import com.example.messagingstatistics.model.dtos.MessageAmountReceivedPerFriendDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageAmountReceivedPerFriendMapper implements RowMapper<MessageAmountReceivedPerFriendDto> {


    @Override
    public MessageAmountReceivedPerFriendDto mapRow(ResultSet resultSet, int i) throws SQLException {
        String username = resultSet.getString("username");
        Long receivedMessages = resultSet.getLong("receivedMessages");

        return new MessageAmountReceivedPerFriendDto(username,receivedMessages);
    }
}
