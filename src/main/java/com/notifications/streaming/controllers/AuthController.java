package com.notifications.streaming.controllers;

import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.entity.ApiResponse;
import com.notifications.streaming.entity.User;
import com.notifications.streaming.mapper.UserMapperImplementation;
import com.notifications.streaming.models.UserAuthentication;
import com.notifications.streaming.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    private final UserMapperImplementation mapperImplementation;

    public AuthController(UserMapperImplementation mapperImplementation, UserService userService) {
        this.mapperImplementation = mapperImplementation;
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> getLoginToken(@RequestBody UserDto userDto) {
        User user = mapperImplementation.dtoToModel(userDto);
        UserAuthentication response = userService.validateUser(user.getUserName(), user.getUserPassword());
        if (response.equals("invalid user")) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) {
        User user = mapperImplementation.dtoToModel(userDto);
        User response = userService.saveUsers(user);
        System.out.println("This is the response from saving user: " + response.toString());
        return ResponseEntity.status(200).body(response.getUserID());
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> result = userService.getAllUsers();
        ApiResponse response = new ApiResponse(result, 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserById/{userid}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable int userid) {
        User result = userService.findUserById(userid);
        ApiResponse apiResponse = new ApiResponse(result, 200);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeAllUsers")
    public ResponseEntity<ApiResponse> DeleteAllUsers() {
        userService.deleteAllUsers();
        ApiResponse apiResponse = new ApiResponse("All users deleted", 200);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}


