package com.example.demo.handler.domain;

import com.example.demo.handler.base.BaseHandler;
import com.example.demo.model.domain.ProfileModel;
import com.example.demo.service.domain.ProfileService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ProfileHandler extends BaseHandler<ProfileModel, UUID> {
    private final ProfileService profileService;

    public ProfileHandler(final ProfileService profileService) {
        super(profileService, ProfileModel.class);
        this.profileService = profileService;
    }

    public Mono<ServerResponse> findByName (final ServerRequest request) {
        String name = request.pathVariable("name");
        return ServerResponse.ok().body(this.profileService.findByName(name), ProfileModel.class);
    }

}
