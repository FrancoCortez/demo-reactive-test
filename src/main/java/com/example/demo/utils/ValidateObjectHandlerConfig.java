package com.example.demo.utils;


import com.example.demo.dto.ViolationErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidateObjectHandlerConfig {

    private final Validator validator;

    public <T> Mono<ServerResponse> requireValidBody(
            Function<Mono<T>, Mono<ServerResponse>> block,
            ServerRequest request, Class<T> bodyClass) {
        return request
                .bodyToMono(bodyClass)
                .flatMap(
                        body -> {
                            Set<ConstraintViolation<T>> violations = this.validator.validate(body);
                            if (violations.isEmpty()) {
                                return block.apply(Mono.just(body));
                            } else {
                                Set<ViolationErrorResponseDto> errorResponse = new HashSet<>();
                                violations.forEach(row -> errorResponse.add(ViolationErrorResponseDto.builder()
                                        .field(row.getPropertyPath().toString())
                                        .violation(row.getMessage())
                                        .build()));
                                return ServerResponse.badRequest().body(Flux.fromIterable(errorResponse), ViolationErrorResponseDto.class);
                            }
                        }
                );
    }
}
