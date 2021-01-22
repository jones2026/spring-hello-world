package com.jones2026.springhello.Greetings;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GreetingService {
    private static final String template = "Hello, %s!";
    public GreetingService() {

    }
    public Mono<String> greet(String name) {

        return Mono.just(String.format(template, name));
    }
}