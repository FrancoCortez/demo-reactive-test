package com.example.demo.handler.base;

import com.example.demo.model.base.BaseIdModel;
import com.example.demo.service.base.BaseService;
import com.example.demo.utils.ValidateObjectHandlerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class BaseHandler<T extends BaseIdModel, ID extends String> {

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

    public Mono<ServerResponse> update(final ServerRequest request) {
        ID id = (ID) request.queryParam("id").orElse("");
        return this.validateObjectHandlerConfig.requireValidBody(body -> this.tidBaseService.update(body.toFuture().join(), id)
                        .flatMap(a -> ServerResponse.ok().body(Mono.justOrEmpty(a), type))
                , request, type);
    }
}
