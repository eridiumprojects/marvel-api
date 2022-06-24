package com.example.mymarvel.api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic characterTopic() {
        return TopicBuilder.name("Character").
                build();
    }

    @Bean
    public NewTopic comicTopic() {
        return TopicBuilder.name("Comic").
                build();
    }

}
