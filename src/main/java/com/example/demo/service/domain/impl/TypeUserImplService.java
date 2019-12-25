package com.example.demo.service.domain.impl;

import com.example.demo.exceptions.MissingParameterException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.domain.TypeUserModel;
import com.example.demo.repository.domain.TypeUserRepository;
import com.example.demo.service.base.impl.BaseImplService;
import com.example.demo.service.domain.TypeUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TypeUserImplService extends BaseImplService<TypeUserModel, UUID> implements TypeUserService {
    private final TypeUserRepository typeUserRepository;

    public TypeUserImplService(TypeUserRepository typeUserRepository) {
        super(typeUserRepository, TypeUserModel.class);
        this.typeUserRepository = typeUserRepository;
    }

    public Mono<TypeUserModel> findByName(String name) {
        if(name == null || name.isBlank() || name.isEmpty()) throw new MissingParameterException("El parametro nombre no esta presente para la busqueda de los tipos de usuario");
        return this.typeUserRepository.findByName(name)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException("El nombre [" + name + "] no se encontro dentro de los tipos de usuario"))));
    }

}
