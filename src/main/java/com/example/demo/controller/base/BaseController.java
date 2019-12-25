package com.example.demo.controller.base;

import com.example.demo.config.api.BaseRestConfig;
import com.example.demo.handler.base.BaseHandler;
import com.example.demo.model.base.BaseIdModel;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class BaseController<T extends BaseIdModel, ID extends UUID> {
    private BaseHandler<T, ID> baseHandler;
    protected String basePath;

    public BaseController(BaseHandler<T, ID> baseHandler, String basePath) {
        this.baseHandler = baseHandler;
        this.basePath = BaseRestConfig.basePath + basePath;
    }

    public RouterFunction<ServerResponse> baseRoutes() {
        return nest(path(basePath),
                        route(POST(""), baseHandler::create)
                                .andRoute(POST("/create/all").and(accept(MediaType.APPLICATION_JSON)), baseHandler::createAll)
                                .andRoute(PUT("/{id}"), baseHandler::update)
                                .andRoute(DELETE("/{id}"), baseHandler::deleteById)
                                .andRoute(DELETE("/all"), baseHandler::deleteAll)
                                .andRoute(GET(""), baseHandler::findAll)
                                .andRoute(GET("/{id}"), baseHandler::findById)
                                .andRoute(GET("/ids"), baseHandler::findByAllId)
                );
    }
}
