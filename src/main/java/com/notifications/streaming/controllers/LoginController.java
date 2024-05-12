package com.notifications.streaming.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.notifications.streaming.models.UserDetails;
import com.notifications.streaming.services.LoginService;
import com.notifications.streaming.services.RBACService;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    RBACService rbacService;

    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDetails request) {
        String Name = request.getName();
        String Role = request.getRole();
        String response = loginService.login(Name, Role, request);
        if (response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(404).body("Unauthorized");
        }
    }

    @PostMapping(value = "/loginAuth")
    public String login(@RequestHeader String token, @RequestBody String username) {

        boolean isValid = rbacService.checkAuth(token, username);
        if (isValid) {
            return "valid token";
        }
        return "invalid token";

    }
}
