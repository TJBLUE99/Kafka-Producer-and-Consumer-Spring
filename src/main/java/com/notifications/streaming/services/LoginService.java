package com.notifications.streaming.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notifications.streaming.Dao.LoginDao;
import com.notifications.streaming.config.JwtUtil;
import com.notifications.streaming.models.UserDetails;

@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    APIDataCacheService apiDataCacheService;

    @Autowired
    LoginDao loginDao;

    UserDetails details = new UserDetails();

    public String login(String username, String Role, UserDetails detailsfromAPI) {

        logger.error("We have missed the cache verifying and adding the object to cache in this iteration");
        details.setEmailId("Test1@ril.com");
        details.setRole("Super_USER");
        details.setName("Test");
        details.setRoleId("1");
        logger.info("user details: " + username);
        if (details.getName().equals(username)) {
            String generatedToken = jwtUtil.generateToken(detailsfromAPI);
            apiDataCacheService.saveDataToCache(detailsfromAPI.getName(), generatedToken);
            return generatedToken;
        } else {
            return "User not found";
        }
    }

    public List<UserDetails> validateUserDB(UserDetails details) {

        return loginDao.validateUserDB(details);
    }
}
