package com.example.messagingstatistics.service.impl;

import com.example.messagingstatistics.service.MessageAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class MessageAnalyticsServiceImpl implements MessageAnalyticsService {


    private final String connectionUrl = "jdbc:mysql://localhost:3306/chatService";
    Logger logger = LoggerFactory.getLogger(MessageAnalyticsServiceImpl.class);

    @Override
    public ResponseEntity<?> getMessageAmountSendPerFriend() {
        try(Connection connection = DriverManager.getConnection(connectionUrl,"root","welcome123")){

            String getMessageAmountSendPerFriendQuery = "SELECT UC.username, COUNT(receiver_id) as sentMessages FROM chatService.message JOIN chatService.user_table UC ON message.receiver_id=UC.id WHERE sender_id=1 GROUP BY receiver_id;";

            PreparedStatement preparedStatement = connection.prepareStatement(getMessageAmountSendPerFriendQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String username = resultSet.getString("username");
                long sentMessages = resultSet.getLong("sentMessages");

                logger.info(username);
                logger.info(String.valueOf(sentMessages));
            }


        }

        catch (SQLException e){

        }
        return null;


    }

    @Override
    public ResponseEntity<?> getMessageAmountReceivedPerFriend() {
        return null;
    }
}
