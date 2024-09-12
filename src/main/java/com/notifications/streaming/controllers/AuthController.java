package com.notifications.streaming.controllers;

import com.notifications.streaming.entity.ApiResponse;
import com.notifications.streaming.entity.User;
import com.notifications.streaming.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> getLoginToken() {
        return null;
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        System.out.println("Request Body: " + user.toString());
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
}


