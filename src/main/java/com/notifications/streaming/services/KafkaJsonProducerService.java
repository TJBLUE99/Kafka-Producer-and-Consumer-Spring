package com.notifications.streaming.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.notifications.streaming.models.AdminNotification;

@Service
public class KafkaJsonProducerService {

    @Autowired
    KafkaTemplate<String, AdminNotification> kafkaTemplate;

    public void sendMessage(AdminNotification adminNotification) {
        org.springframework.messaging.Message<AdminNotification> message = MessageBuilder
                .withPayload(adminNotification)
                .setHeader(KafkaHeaders.TOPIC, "admin-notfication-topic")
                .build();

        kafkaTemplate.send(message);
    }

}
