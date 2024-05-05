package com.notifications.streaming.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.notifications.streaming.models.AdminNotification;

@Service
public class KafkaConsumerService {

    Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final List<AdminNotification> messages = new ArrayList<>();

    // @KafkaListener(topics = "admin-notfication-topic", groupId = "group-1")
    public void Updatednotification(String value) {
        logger.info("Consuming the message from topic: " + value);
        // messages.add(value);
    }

    @KafkaListener(topics = "admin-notfication-topic", groupId = "group-1")
    public void UpdatedJsonnotification(AdminNotification adminNotification) {
        logger.info("Consuming the message from topic: " + adminNotification.toString());
        messages.add(adminNotification);
        logger.info("These are the messages from the List: " + messages.get(0).toString());
    }

    public List<AdminNotification> getMessages() {
        List<AdminNotification> currentMessages = new ArrayList<>(messages);
        logger.info("Inside get all messages service and these are current messages: ", currentMessages);
        messages.clear();
        return currentMessages;
    }

}
