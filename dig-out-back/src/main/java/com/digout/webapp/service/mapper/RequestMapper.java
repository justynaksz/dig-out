package com.digout.webapp.service.mapper;

import com.digout.webapp.repository.role.Role;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.request.SignUpRequest;
import com.digout.webapp.service.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {

    private final PasswordEncoder passwordEncoder;
    private final AppUserValidator appUserValidator;

    @Autowired
    public RequestMapper(PasswordEncoder passwordEncoder, AppUserValidator appUserValidator) {
        this.passwordEncoder = passwordEncoder;
        this.appUserValidator = appUserValidator;
    }

    public AppUserDTO mapRequest(SignUpRequest signupRequest) throws InvalidInputException, EmptyFieldException {
        var nickname = signupRequest.name();
        var email = signupRequest.email();
        appUserValidator.validatePassword(signupRequest.password());
        var password = passwordEncoder.encode(signupRequest.password());
        return new AppUserDTO(0, nickname, password, email, Role.USER, null, null);
    }
}
