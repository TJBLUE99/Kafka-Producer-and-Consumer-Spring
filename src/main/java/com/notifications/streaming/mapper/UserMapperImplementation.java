package com.notifications.streaming.mapper;

import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImplementation implements UserMapper {

    @Override
    public UserDto modelToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setUserID(user.getUserID());
        userDto.setUserPassword(user.getUserPassword());
        return userDto;
    }

    @Override
    public User dtoToModel(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserID(userDto.getUserID());
        user.setUserPassword(userDto.getUserPassword());
        return user;
    }
}
