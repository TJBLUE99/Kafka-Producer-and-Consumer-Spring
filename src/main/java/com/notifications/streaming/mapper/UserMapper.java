package com.notifications.streaming.mapper;

import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.entity.User;

public interface UserMapper {

    UserDto modelToDto(User user);

    User dtoToModel(UserDto userDto);

}
