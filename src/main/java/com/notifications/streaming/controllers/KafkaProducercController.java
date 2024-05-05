package com.notifications.streaming.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.notifications.streaming.models.AdminNotification;
import com.notifications.streaming.services.KafkaJsonProducerService;
import com.notifications.streaming.services.KafkaProducerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class KafkaProducercController {

    Logger logger = LoggerFactory.getLogger(KafkaProducercController.class);

    @Autowired
    private KafkaJsonProducerService kafkaJsonProducerService;

    // @Autowired
    // private KafkaProducerService kafkaProducerService;

    // @PostMapping("/updateMessage")
    // public ResponseEntity<?> updateAdmin() {
    // for (int i = 0; i <= 10; i++) {
    // kafkaProducerService.UpdateAdminNotification("updated my" + i + "th" +
    // "notification");

    // }
    // return new ResponseEntity<>(Map.of("message", " Notification updated"),
    // HttpStatus.OK);

    // }

    @PostMapping("/json")
    @ResponseBody
    public ResponseEntity<?> getMessage(@RequestBody AdminNotification message) {

        logger.info("message: " + message.getMessage());
        logger.info("Org name: " + message.getOrganizationName());
        logger.info("plan name: " + message.getPlanName());
        logger.info("status: " + message.getStatus());

        if (message.getOrganizationName() == null) {
            return new ResponseEntity<>(Map.of("message", " No data"), HttpStatus.BAD_REQUEST);
        }
        kafkaJsonProducerService.sendMessage(message);

        return new ResponseEntity<>(Map.of("message", " Notification updated as Json"), HttpStatus.OK);
    }

}
