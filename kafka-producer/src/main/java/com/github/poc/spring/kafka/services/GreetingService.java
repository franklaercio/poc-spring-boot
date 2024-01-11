package com.github.poc.spring.kafka.services;

import com.github.poc.spring.kafka.configs.KafkaTopicProperties;
import com.github.poc.spring.kafka.domain.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final KafkaTopicProperties kafkaTopicProperties;

    public GreetingService(KafkaTemplate<String, Object> kafkaTemplate, KafkaTopicProperties kafkaTopicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicProperties = kafkaTopicProperties;
    }

    public void sendMessage(Greeting greeting) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(kafkaTopicProperties.getGreeting(), greeting);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    LOGGER.info("Sent message=[{}] with offset=[{}]", greeting, result.getRecordMetadata().offset());
                } else {
                    LOGGER.error("Unable to send message=[{}] due to : {}", greeting, ex.getMessage());
                }
            });

        } catch (Exception ex) {
            LOGGER.error("Could not sent a message : {}", ex.getMessage());
        }
    }
}
