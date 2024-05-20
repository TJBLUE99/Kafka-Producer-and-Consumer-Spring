package com.notifications.streaming.services;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class APIDataCacheService {

    Logger logger = LoggerFactory.getLogger(APIDataCacheService.class);

    private static final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public void saveDataToCache(String Key, String token) {
        logger.info("Key: " + Key + " " + "Token: " + token);
        cache.put(Key, token);
    }

    public String getDataFromCache(String Key) {
        logger.info("Key revcieved: " + Key);
        logger.info("This is the information in the cache: " + cache.get(Key));
        return cache.get(Key);
    }

    public String clearCache(String key) {
        return cache.remove(key);
    }

}
