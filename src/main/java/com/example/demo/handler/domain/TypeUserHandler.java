package com.example.demo.handler.domain;

import com.example.demo.model.TypeUserModel;
import com.example.demo.service.domain.TypeUserService;
import com.example.demo.utils.ValidateObjectHandlerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TypeUserHandler {

    private final TypeUserService typeUserService;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;

    public Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body -> this.typeUserService.create(body.toFuture().join())
                        .flatMap(a -> ServerResponse.ok().body(Mono.justOrEmpty(a), TypeUserModel.class))
                , request, TypeUserModel.class);
    }
}
