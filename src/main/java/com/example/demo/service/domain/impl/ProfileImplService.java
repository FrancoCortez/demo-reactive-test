package com.example.demo.service.domain.impl;

import com.example.demo.exceptions.MissingParameterException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.domain.ProfileModel;
import com.example.demo.repository.domain.ProfileRepository;
import com.example.demo.service.base.impl.BaseImplService;
import com.example.demo.service.domain.ProfileService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProfileImplService extends BaseImplService<ProfileModel, UUID> implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileImplService(ProfileRepository profileRepository) {
        super(profileRepository, ProfileModel.class);
        this.profileRepository = profileRepository;
    }

    public Mono<ProfileModel> findByName(String name) {
        if(name == null || name.isBlank() || name.isEmpty()) throw new MissingParameterException("El parametro nombre no esta presente para la busqueda del perfil");
        return this.profileRepository.findByName(name)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException("El nombre [" + name + "] no se encontro dentro de los perfiles"))));
    }

}
