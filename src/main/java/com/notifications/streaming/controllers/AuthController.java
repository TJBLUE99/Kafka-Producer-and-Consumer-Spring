package com.notifications.streaming.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
public class AuthController {

    @PostMapping(value = "/login")
    public ResponseEntity<?> getLoginToken() {
        return null;
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> createNewUser(HttpRequest request) {
        System.out.println(request.getHeaders());
        return ResponseEntity.status(200).body("received");
    }
}


