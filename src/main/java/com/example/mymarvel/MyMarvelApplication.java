package com.example.mymarvel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MyMarvelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMarvelApplication.class);
    }

}
