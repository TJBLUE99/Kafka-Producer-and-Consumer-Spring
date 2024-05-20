package com.notifications.streaming.models;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserAuthentication {

    private String token;
    private String Username;

}
