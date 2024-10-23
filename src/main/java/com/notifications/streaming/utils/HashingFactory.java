package com.notifications.streaming.utils;

import org.springframework.stereotype.Component;

@Component
public interface HashingFactory {

    String passwordHasher(String password);

    boolean checkPassword(String encryptedPassword, String password);
    
}
