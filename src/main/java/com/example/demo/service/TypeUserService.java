package com.example.demo.service;

import com.example.demo.dto.TypeUserDto;
import com.example.demo.repository.TypeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class TypeUserService {
    private final TypeUserRepository typeUserRepository;

    public Mono<TypeUserDto> create(TypeUserDto dto) {
        return this.typeUserRepository.save(dto);
    }

    public Mono<TypeUserDto> findByName(String name) {
        return this.typeUserRepository.findByName(name);
    }

}
