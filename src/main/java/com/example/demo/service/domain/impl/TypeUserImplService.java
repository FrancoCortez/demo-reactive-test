package com.example.demo.service.domain.impl;

import com.example.demo.model.TypeUserModel;
import com.example.demo.repository.TypeUserRepository;
import com.example.demo.service.base.impl.BaseImplService;
import com.example.demo.service.domain.TypeUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TypeUserImplService extends BaseImplService<TypeUserModel, String> implements TypeUserService {
    private final TypeUserRepository typeUserRepository;

    public TypeUserImplService(TypeUserRepository typeUserRepository) {
        super(typeUserRepository, TypeUserModel.class);
        this.typeUserRepository = typeUserRepository;
    }

    public Mono<TypeUserModel> findByName(String name) {
        return this.typeUserRepository.findByName(name);
    }

}
