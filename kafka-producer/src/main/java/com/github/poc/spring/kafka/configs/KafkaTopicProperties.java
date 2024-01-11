package com.github.poc.spring.kafka.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicProperties {

    @Value("${kafka.topic.greeting}")
    private String greeting;

    public String getGreeting() {
        return greeting;
    }
}
