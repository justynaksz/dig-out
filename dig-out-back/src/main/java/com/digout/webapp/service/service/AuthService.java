package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.AppUser;
import com.digout.webapp.repository.repository.AppUserRepository;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.DuplicateException;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.AppUserMapper;
import com.digout.webapp.service.mapper.RequestMapper;
import com.digout.webapp.service.request.SignInRequest;
import com.digout.webapp.service.request.SignUpRequest;
import com.digout.webapp.service.response.RefreshResponse;
import com.digout.webapp.service.response.SignInResponse;
import com.digout.webapp.service.validator.AppUserValidator;
import com.digout.webapp.web.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final RequestMapper requestMapper;
    private final AppUserValidator appUserValidator;
    private final AppUserMapper appUserMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(AppUserRepository appUserRepository, RequestMapper requestMapper,
                       AppUserValidator appUserValidator, AppUserMapper appUserMapper, JwtUtils jwtUtils,
                       AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.requestMapper = requestMapper;
        this.appUserValidator = appUserValidator;
        this.appUserMapper = appUserMapper;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public AppUserDTO signUp(SignUpRequest signupRequest) throws InvalidInputException,
            EmptyFieldException, DuplicateException {
        verifyAlreadyExists(signupRequest);
        AppUserDTO appUserDTO = requestMapper.mapRequest(signupRequest);
        appUserValidator.isValid(appUserDTO);
        var appUser = appUserMapper.toModel(appUserDTO);
        return appUserMapper.toDTO(appUserRepository.save(appUser));
    }

    private void verifyAlreadyExists(SignUpRequest signupRequest) throws DuplicateException {
        verifyAlreadyExists(appUserRepository.findByEmail(signupRequest.email()));
        verifyAlreadyExists(appUserRepository.findByNickname(signupRequest.name()));
    }

    private void verifyAlreadyExists(AppUser existingUser) throws DuplicateException {
        if (existingUser != null) {
            throw new DuplicateException();
        }
    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        var nickname = signInRequest.nickname();
        var password = signInRequest.password();
        var signInResponse = new SignInResponse();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(nickname, password));
            var appUser = appUserRepository.findByNickname(nickname);
            var token = jwtUtils.generateToken(nickname);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), nickname);
            signInResponse.setStatusCode(200);
            signInResponse.setToken(token);
            signInResponse.setRefreshToken(refreshToken);
            signInResponse.setExpirationTime("60minutes");
            signInResponse.setMessage("Successfully signed in.");
            signInResponse.setUserId(appUser.getId());
        } catch (Exception e) {
            signInResponse.setStatusCode(500);
            signInResponse.setError(e.getMessage());
        }
        return signInResponse;
    }

    public RefreshResponse refreshToken(String refreshTokenRequest) throws NotFoundException {
        var refreshTokenResponse = new RefreshResponse();
        var nickname = jwtUtils.extractNickname(refreshTokenRequest);
        validateUserByNickname(nickname);
        if(jwtUtils.isTokenValid(refreshTokenRequest, nickname)) {
            var token = jwtUtils.generateToken(nickname);
            refreshTokenResponse.setStatusCode(200);
            refreshTokenResponse.setToken(token);
            refreshTokenResponse.setRefreshToken(refreshTokenRequest);
            refreshTokenResponse.setExpirationTime("60minutes");
            refreshTokenResponse.setMessage("Token successfully refreshed.");
        } else {
            refreshTokenResponse.setStatusCode(500);
            refreshTokenResponse.setMessage("Invalid token.");
        }
        return refreshTokenResponse;
    }

    private void validateUserByNickname(String nickname) throws NotFoundException {
        var appUser = appUserRepository.findByNickname(nickname);
        if (appUser == null) {
            throw new NotFoundException("User of nickname: " + nickname + " not found.");
        }
    }
}
