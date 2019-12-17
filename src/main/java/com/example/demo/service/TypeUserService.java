package com.example.demo.service;

import com.example.demo.dto.TypeUserDto;
import reactor.core.publisher.Mono;

public interface TypeUserService {

    Mono<TypeUserDto> create(TypeUserDto dto);

    Mono<TypeUserDto> findByName(String name);
}
