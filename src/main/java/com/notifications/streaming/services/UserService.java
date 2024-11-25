package com.notifications.streaming.services;

import com.notifications.streaming.Exception.UserNotFoundException;
import com.notifications.streaming.config.JwtUtil;
import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.entity.User;
import com.notifications.streaming.mapper.UserMapperImplementation;
import com.notifications.streaming.models.UserAuthentication;
import com.notifications.streaming.repository.UserRepository;
//import com.notifications.streaming.utils.BcryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserMapperImplementation userMapperImplementation;
    private final JwtUtil jwtUtil;
//    private final BcryptUtil bcryptUtil;

    public UserService(UserMapperImplementation userMapperImplementation, JwtUtil jwtUtil
    ) {
        this.userMapperImplementation = userMapperImplementation;
        this.jwtUtil = jwtUtil;

    }

    @Autowired
    UserRepository userRepository;

    public User saveUsers(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public UserAuthentication validateUser(String username, String password) throws UserNotFoundException {
        User user = userRepository.findByUserName(username);
        UserDto userDto = userMapperImplementation.modelToDto(user);
        if (userDto.getUserName().equals(username) && userDto.getUserPassword().equals(password)) {
            String token = jwtUtil.generateToken(userDto);
            UserAuthentication userAuthentication = new UserAuthentication();
            userAuthentication.setToken(token);
            userAuthentication.setUsername(userDto.getUserName());
            return userAuthentication;
        } else {
            return new UserAuthentication();
        }
    }


}
