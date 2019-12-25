package com.example.demo.handler.base;

import com.example.demo.model.base.BaseIdModel;
import com.example.demo.model.domain.TypeUserModel;
import com.example.demo.service.base.BaseService;
import com.example.demo.utils.ValidateObjectHandlerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class BaseHandler<T extends BaseIdModel, ID extends UUID> {
    private BaseService<T, ID> tidBaseService;
    private Class<T> type;
    @Autowired
    private ValidateObjectHandlerConfig validateObjectHandlerConfig;

    public BaseHandler(BaseService<T, ID> tidBaseService, Class<T> type) {
        this.tidBaseService = tidBaseService;
        this.type = type;
    }

    public Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body -> this.tidBaseService.create(body.toFuture().join())
                        .flatMap(a -> ServerResponse.ok().body(Mono.justOrEmpty(a), type))
                , request, type);
    }

//    public Mono<ServerResponse> createAll (final ServerRequest request) {
//        return request.bodyToFlux(new ParameterizedTypeReference<List<T>>() {
//        }).map(map -> ServerResponse.ok().body(this.tidBaseService.createAll(map), type));
//    }

    public Mono<ServerResponse> createAll(final ServerRequest request) {
        Flux<T> resp = this.tidBaseService.createAll(request.bodyToFlux(type));
        return ServerResponse.ok().body(resp, type);
    }

    public Mono<ServerResponse> update(final ServerRequest request) {
        ID id = (ID) UUID.fromString(request.pathVariable("id"));
        return this.validateObjectHandlerConfig.requireValidBody(body -> this.tidBaseService.update(body.toFuture().join(), id)
                        .flatMap(a -> ServerResponse.ok().body(Mono.justOrEmpty(a), type))
                , request, type);
    }

    public Mono<ServerResponse> deleteById(final ServerRequest request) {
        ID id = (ID) UUID.fromString(request.pathVariable("id"));
        return ServerResponse.ok().body(this.tidBaseService.deleteById(id), Void.class);
    }

    public Mono<ServerResponse> deleteAll(final ServerRequest request) {
        return ServerResponse.ok().body(this.tidBaseService.deleteAll(), Void.class);
    }

    public Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().body(this.tidBaseService.findAll(), TypeUserModel.class);
    }

    public Mono<ServerResponse> findById(final ServerRequest request) {
        ID id = (ID) UUID.fromString(request.pathVariable("id"));
        return ServerResponse.ok().body(this.tidBaseService.findById(id), TypeUserModel.class);
    }

    public Mono<ServerResponse> findByAllId(final ServerRequest request) {
        List<String> requestIds = new ArrayList<>(request.queryParams().get("ids"));
        List<ID> ids = requestIds.stream().map(mapper -> (ID) UUID.fromString(mapper)).collect(Collectors.toList());
        return ServerResponse.ok().body(this.tidBaseService.findByAllId(ids), TypeUserModel.class);
    }
}
