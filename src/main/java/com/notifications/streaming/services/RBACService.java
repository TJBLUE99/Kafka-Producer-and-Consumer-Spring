package com.notifications.streaming.services;

import org.apache.kafka.clients.admin.UserScramCredentialAlteration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notifications.streaming.Dao.RBACDao;
import com.notifications.streaming.config.JwtUtil;
import com.notifications.streaming.models.UserDetails;

@Service
public class RBACService {

    @Autowired
    RBACDao rbacDao;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    APIDataCacheService apiDataCacheService;

    Logger logger = LoggerFactory.getLogger(RBACService.class);

    public boolean checkAuth(String token, String username) {

        String response = apiDataCacheService.getDataFromCache(username);
        logger.info("This is token from the header: " + token);
        logger.info("This is token from the cache: " + response);

        boolean isExpired = jwtUtil.isTokenExpired(response);
        if (isExpired) {
            apiDataCacheService.clearCache(response);
            return false;
        } else {
            logger.info("this is response" + response);
            String cacheData = jwtUtil.getUserName(response);
            logger.info("This is the data in cache: " + cacheData);
            String UserData = jwtUtil.getUserName(token);
            logger.info("Token data from the user: " + UserData);
            if (UserData.equals(cacheData)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public UserDetails getRBACDetails() {
        return rbacDao.getRBACDetails();
    }

}
