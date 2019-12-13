package com.example.demo.controller;

import com.example.demo.config.error.GlobalErrorAttributes;
import com.example.demo.dto.TypeUserDto;
import com.example.demo.service.TypeUserService;
import com.example.demo.utils.ValidateObjectHandlerConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@Import(TypeUserController.class)
public class TypeUserControllerTest {

    @MockBean
    private TypeUserService typeUserService;
    @Autowired
    private WebTestClient client;
    @SpyBean
    private ValidateObjectHandlerConfig validateObjectHandlerConfig;
    @SpyBean
    private GlobalErrorAttributes errorAttributes;
    @MockBean
    private ApplicationContext ctx;

    @BeforeTestMethod
    public void setUp() {
        this.validateObjectHandlerConfig = ctx.getBean(ValidateObjectHandlerConfig.class);
        this.errorAttributes = ctx.getBean(GlobalErrorAttributes.class);
    }


    @Test
    public void createTypeUser() throws Exception {
        TypeUserDto typeUserDto = TypeUserDto.builder()
                .name("test")
                .description("test description")
                .build();
        Mockito.when(this.typeUserService.create(typeUserDto))
                .thenReturn(Mono.just(TypeUserDto.builder()
                        .id("1")
                        .name("test")
                        .description("test description")
                        .build()));

        this.client.post()
                .uri("/test")
                .bodyValue(typeUserDto)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody()
                .jsonPath("id").isEqualTo("1")
                .jsonPath("name").isEqualTo("test")
                .jsonPath("description").isEqualTo("test description");

    }

    @Test
    public void createTypeUserOnNamedNull() throws Exception {
        TypeUserDto typeUserDto = TypeUserDto.builder()
                .name(null)
                .description("test description")
                .build();
        Mockito.when(this.typeUserService.create(typeUserDto))
                .thenReturn(Mono.just(TypeUserDto.builder()
                        .id("1")
                        .name(null)
                        .description("test description")
                        .build()));

        this.client.post()
                .uri("/test")
                .bodyValue(typeUserDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody()
                .jsonPath("$.[0]field").isEqualTo("name")
                .jsonPath("$.[0]violation").isEqualTo("no puede ser null");

    }
}
