package com.example.demo.controller;

import com.example.demo.config.error.GlobalErrorAttributes;
import com.example.demo.controller.domain.TypeUserController;
import com.example.demo.handler.domain.TypeUserHandler;
import com.example.demo.model.domain.TypeUserModel;
import com.example.demo.service.domain.TypeUserService;
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

import java.util.UUID;

@WebFluxTest
@Import({TypeUserController.class, TypeUserHandler.class})
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
        TypeUserModel typeUserDto = TypeUserModel.builder()
                .name("test")
                .description("test description")
                .build();
        TypeUserModel typeUserDtoResp = TypeUserModel.builder()
                .name("test")
                .description("test description")
                .build();
        typeUserDtoResp.setId(UUID.fromString("1"));
        Mockito.when(this.typeUserService.create(typeUserDto))
                .thenReturn(Mono.just(typeUserDtoResp));

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
        TypeUserModel typeUserDto = TypeUserModel.builder()
                .name(null)
                .description("test description")
                .build();
        TypeUserModel typeUserDtoResp = TypeUserModel.builder()
                .name(null)
                .description("test description")
                .build();
        typeUserDtoResp.setId(UUID.fromString("1"));
        typeUserDtoResp.setId(UUID.fromString("1"));
        Mockito.when(this.typeUserService.create(typeUserDto))
                .thenReturn(Mono.just(typeUserDtoResp));

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
