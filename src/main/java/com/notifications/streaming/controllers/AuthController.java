package com.notifications.streaming.controllers;

import com.notifications.streaming.Exception.UserNotFoundException;
import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.models.UserAuthentication;
import com.notifications.streaming.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> getLoginToken(@RequestBody UserDto userDto) throws UserNotFoundException {
        logger.info("This is user dto {}" , userDto.toString());
        UserAuthentication response = userService.validateUser(userDto.getUserName(), userDto.getUserPassword());
        if (response.equals("invalid user")) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @PostMapping(value = "/refreshToken")
    public ResponseEntity<?> getRefreshToken(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body("Refresh token in the response");
    }


}


