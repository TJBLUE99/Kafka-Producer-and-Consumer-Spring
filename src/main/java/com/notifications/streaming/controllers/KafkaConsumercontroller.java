package com.notifications.streaming.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notifications.streaming.models.AdminNotification;
import com.notifications.streaming.services.KafkaConsumerService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/consume")
public class KafkaConsumercontroller {

    Logger logger = LoggerFactory.getLogger(KafkaConsumercontroller.class);

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllMessages() {
        List<AdminNotification> response = kafkaConsumerService.getMessages();
        logger.info("Response from the consumer all service: " + response.toString());

        if (response.isEmpty()) {
            return new ResponseEntity<>(Map.of("Message", "No response", "Status code", HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
