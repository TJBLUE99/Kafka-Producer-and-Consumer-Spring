package com.notifications.streaming.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.notifications.streaming.services.Sampleservice;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@RestController
public class Samplecontroller {

    @Autowired
    Sampleservice sampleservice;

    @GetMapping(value = "/getSample")
    public String getSampleData() {
        return sampleservice.getSampleData();
    }

    @GetMapping(path = "/stream-flux", produces = org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(10))
                .map(sequence -> "Flux" + LocalTime.now().toString());
    }

    @GetMapping(path = "/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(30))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }
}
