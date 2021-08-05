package com.example.messagingstatistics.model.mapper;

import com.example.messagingstatistics.model.dtos.MessageAmountSentPerFriendDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageAmountSentPerFriendMapper implements RowMapper<MessageAmountSentPerFriendDto> {

    @Override
    public MessageAmountSentPerFriendDto mapRow(ResultSet resultSet, int i) throws SQLException {
        String username = resultSet.getString("username");
        Long sentMessages = resultSet.getLong("sentMessages");

        return new MessageAmountSentPerFriendDto(username,sentMessages);
    }
}
