package com.github.poc.spring.kafka.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "greetings", groupId = "greeting-consumer-group")
    public void consumeEvents(String greeting) {
        LOGGER.info("Consumer the events {} ", greeting);
    }
}
