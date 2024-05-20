package com.notifications.streaming.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.notifications.streaming.models.UserDetails;
import com.notifications.streaming.services.RBACService;

@RestController
public class RBACController {

    @Autowired
    RBACService rbacService;

    @GetMapping("/getRBACDetails/:{id}")
    public UserDetails getRBACDetailsById(@PathVariable int id) {
        return rbacService.getRBACDetails();
    }

}
