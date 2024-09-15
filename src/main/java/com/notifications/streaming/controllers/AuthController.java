package com.notifications.streaming.controllers;

import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.entity.ApiResponse;
import com.notifications.streaming.entity.User;
import com.notifications.streaming.mapper.UserMapperImplementation;
import com.notifications.streaming.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.DoubleToIntFunction;

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    private final UserMapperImplementation mapperImplementation;

    public AuthController(UserMapperImplementation mapperImplementation) {
        this.mapperImplementation = mapperImplementation;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> getLoginToken(@RequestBody User user) {
        logger.info("Entered login function to get the token:" + user);
        return null;
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) {
        System.out.println("Request Body: " + userDto.toString());
        User user = mapperImplementation.dtoToModel(userDto);
        System.out.println("After conversion: " + user + " entity");
        User response = userService.saveUsers(user);
        System.out.println("This is the response from saving user: " + response.toString());
        return ResponseEntity.status(200).body("received");
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> result = userService.getAllUsers();
        System.out.println("List of all users: " + result.toString());
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


