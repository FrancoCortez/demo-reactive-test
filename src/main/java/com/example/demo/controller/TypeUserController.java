package com.example.demo.controller;


import com.example.demo.handler.domain.TypeUserHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class TypeUserController {


    @Bean
    public RouterFunction<ServerResponse> routes(final TypeUserHandler typeUserHandler) {
        return route(POST("/test"), typeUserHandler::create);
    }
}
