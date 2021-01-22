package com.jones2026.springhello.Greetings;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class GreetingController {

    private final GreetingService service;

    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @RequestMapping("/greeting")
    public Mono<ResponseEntity<String>> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return service.greet(name)
                .map(r -> ResponseEntity.ok().body(r))
                ;
    }

}