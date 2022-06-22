package com.example.mymarvel.api.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "Character",
            groupId = "groupId")
    void character(String data) {
        System.out.println("Listener class character received: " + data + "");
    }

    @KafkaListener(topics = "Comic",
            groupId = "groupId")
    void comic(String data) {
        System.out.println("Listener class comic received: " + data + "");
    }
}
