package com.example.demo.controller.domain;


import com.example.demo.controller.base.BaseController;
import com.example.demo.handler.domain.TypeUserHandler;
import com.example.demo.model.domain.TypeUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class TypeUserController extends BaseController<TypeUserModel, UUID> {

//
//    @Bean
//    public RouterFunction<ServerResponse> routes(final TypeUserHandler typeUserHandler) {
//        return nest(path(BaseRestConfig.basePath),
//                nest(path("/type-user"),
//                        route(POST(""), typeUserHandler::create)
//                                .andRoute(POST("/create/all").and(accept(MediaType.APPLICATION_JSON)), typeUserHandler::createAll)
//                                .andRoute(PUT("/{id}"), typeUserHandler::update)
//                                .andRoute(DELETE("/{id}"), typeUserHandler::deleteById)
//                                .andRoute(DELETE("/all"), typeUserHandler::deleteAll)
//                                .andRoute(GET(""), typeUserHandler::findAll)
//                                .andRoute(GET("/{id}"), typeUserHandler::findById)
//                                .andRoute(GET("/ids"), typeUserHandler::findByAllId)
//                ));
//    }

    private TypeUserHandler handler;

    public TypeUserController(TypeUserHandler handler) {
        super(handler, "/type-user");
        this.handler = handler;
    }

    @Bean(name = "type-user-routes")
    public RouterFunction<ServerResponse> routes() {
        return this.baseRoutes().andNest(RequestPredicates.path(this.basePath),
                route(RequestPredicates.GET("/find/by/name/{name}"), this.handler::findByName)
        );
    }
}
