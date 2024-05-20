package com.notifications.streaming.Dao;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.notifications.streaming.models.UserDetails;

@Component
public class LoginDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserDetails> validateUserDB(UserDetails details) {

        String username = details.getName();

        String sql = "Select * from user_login_details where USER_NAME = ?";
        return jdbcTemplate.query(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            return preparedStatement;
        }, (resultSet, rowNum) -> {
            UserDetails userDetails = new UserDetails();
            userDetails.setName(resultSet.getString("USER_NAME"));
            userDetails.setEmailId(resultSet.getString("USER_EMAILID"));
            return userDetails;
        });

    }

}
