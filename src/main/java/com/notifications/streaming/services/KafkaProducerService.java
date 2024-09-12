//package com.notifications.streaming.services;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducerService {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
//
//    public boolean UpdateAdminNotification(String notification) {
//        kafkaTemplate.send("admin-notfication-topic", notification);
//        logger.info("message produced");
//        return true;
//    }
//
//}
