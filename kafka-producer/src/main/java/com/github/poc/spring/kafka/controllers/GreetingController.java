package com.github.poc.spring.kafka.controllers;

import com.github.poc.spring.kafka.controllers.dtos.MessageDTO;
import com.github.poc.spring.kafka.domain.Greeting;
import com.github.poc.spring.kafka.services.GreetingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public void said(@RequestBody MessageDTO messageDTO) {
        this.greetingService.sendMessage(new Greeting(messageDTO.message()));
    }
}
