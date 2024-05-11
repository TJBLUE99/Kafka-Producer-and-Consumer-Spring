package com.notifications.streaming.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.notifications.streaming.config.JwtUtil;
import com.notifications.streaming.models.UserDetails;

@RestController
public class LoginController {

    @Autowired
    JwtUtil jwtUtil;

    @ResponseBody
    @PostMapping(value = "/login")
    public String login(@RequestBody UserDetails request) {
        String token = jwtUtil.generateToken(request);
        return token;

    }

    @PostMapping(value = "/loginAuth")
    public String login(@RequestHeader String token) {
        if (!jwtUtil.isTokenExpired(token)) {
            return Map.of("name", jwtUtil.getUserName(token)).toString();
        } else {
            return "Expired";
        }
    }

}
