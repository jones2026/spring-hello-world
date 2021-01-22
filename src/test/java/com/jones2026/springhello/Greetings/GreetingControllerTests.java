package com.jones2026.springhello.Greetings;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jones2026.springhello.Application;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
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

        webTestClient
                .get().uri("/greeting?name=Mock")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value( v -> v.equalsIgnoreCase(GREET));
    }
}
