package com.notifications.streaming.Dao;

import org.springframework.stereotype.Component;

import com.notifications.streaming.models.UserDetails;

@Component
public class RBACDao {

    public UserDetails getRBACDetails() {
        // Sample details
        UserDetails details = new UserDetails();
        details.setEmailId("Test1@ril.com");
        details.setRole("Super_USER");
        details.setName("Test");
        details.setRoleId("1");
        return details;
    }

}
