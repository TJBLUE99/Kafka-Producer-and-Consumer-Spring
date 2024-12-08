package com.notifications.streaming.controllers;

import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.entity.ApiResponse;
import com.notifications.streaming.entity.User;
import com.notifications.streaming.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.notifications.streaming.mapper.UserMapperImplementation;

import java.util.List;

@RequestMapping("/api/user")
@RestController
@CrossOrigin(value = "*")
public class UsersController {

    private final UserMapperImplementation mapperImplementation;
    private final UserService userService;

    public UsersController(UserMapperImplementation mapperImplementation, UserService userService) {
        this.mapperImplementation = mapperImplementation;
        this.userService = userService;
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
