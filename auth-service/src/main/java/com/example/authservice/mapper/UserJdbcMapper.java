package com.example.authservice.mapper;

import com.example.authservice.model.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJdbcMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        return new User(username,password);
    }
}
