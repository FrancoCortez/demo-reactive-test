package com.example.demo.controller;


import com.example.demo.dto.TypeUserDto;
import com.example.demo.service.TypeUserService;
import com.example.demo.utils.ValidateObjectHandlerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class TypeUserController {


    @Bean
    public RouterFunction<ServerResponse> routes(final TypeUserService typeUserService, final ValidateObjectHandlerConfig validateObjectHandlerConfig) {
        return route(POST("/test"), request -> validateObjectHandlerConfig.requireValidBody(body -> typeUserService.create(body.toFuture().join()).flatMap(a -> ServerResponse.ok().body(Mono.justOrEmpty(a), TypeUserDto.class)), request, TypeUserDto.class));
    }
}
