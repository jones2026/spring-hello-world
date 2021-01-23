package com.jones2026.springhello.Greetings;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

@WebFluxTest(GreetingController.class)
public class GreetingControllerTests {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    private GreetingService service;
    private final static String GREET = "greeting service called";
    Mono<String> GREETING_SERVICE_CALLED = Mono.just(GREET);

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.greet(anyString())).thenReturn(GREETING_SERVICE_CALLED);

        webTestClient.get().uri("/greeting?name=Mock").exchange().expectStatus().isOk().expectBody(String.class)
                .value(v -> v.equalsIgnoreCase(GREET));
    }
}
