package com.example.demo.handler.domain;

import com.example.demo.handler.base.BaseHandler;
import com.example.demo.model.domain.ProfileModel;
import com.example.demo.model.domain.TypeUserModel;
import com.example.demo.service.domain.TypeUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TypeUserHandler extends BaseHandler<TypeUserModel, UUID> {
    private final TypeUserService typeUserService;

    public TypeUserHandler(final TypeUserService typeUserService) {
        super(typeUserService, TypeUserModel.class);
        this.typeUserService = typeUserService;
    }
    public Mono<ServerResponse> findByName (final ServerRequest request) {
        String name = request.pathVariable("name");
        return ServerResponse.ok().body(this.typeUserService.findByName(name), TypeUserModel.class);
    }
}
