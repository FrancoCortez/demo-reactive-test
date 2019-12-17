package com.example.demo.service.impl;

import com.example.demo.dto.TypeUserDto;
import com.example.demo.repository.TypeUserRepository;
import com.example.demo.service.TypeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TypeUserImplService implements TypeUserService {
    private final TypeUserRepository typeUserRepository;

    public Mono<TypeUserDto> create(TypeUserDto dto) {
        return this.typeUserRepository.save(dto);
    }

    public Mono<TypeUserDto> findByName(String name) {
        return this.typeUserRepository.findByName(name);
    }

}
