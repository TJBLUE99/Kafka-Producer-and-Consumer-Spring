package com.notifications.streaming.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptUtil implements HashingFactory {

    private final String PASSWORD_SECRET = "";

    private final String SALT = "";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public BcryptUtil(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String passwordHasher(String password) {
        System.out.println(passwordEncoder.encode(password));
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean checkPassword(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }

}
