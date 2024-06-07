package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.AppUser;
import com.digout.webapp.repository.repository.AppUserRepository;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.AppUserMapper;
import com.digout.webapp.service.validator.AppUserValidator;
import com.digout.webapp.service.validator.ResultValidator;
import com.digout.webapp.web.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final AppUserValidator appUserValidator;
    private final ResultValidator<AppUser> resultValidator;

    private final JwtUtils jwtUtils;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository,
                          AppUserMapper appUserMapper,
                          AppUserValidator appUserValidator,
                          ResultValidator<AppUser> resultValidator,
                          JwtUtils jwtUtils) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.appUserValidator = appUserValidator;
        this.resultValidator = resultValidator;
        this.jwtUtils = jwtUtils;
    }

    public List<AppUserDTO> getAll() throws EmptyResultException {
        var appUsers = appUserRepository.findAll();
        resultValidator.verifyNotNullOrEmptyList(appUsers);
        return appUserMapper.convertModelToDTO(appUsers);
    }

    public AppUserDTO getById(int id) throws InvalidInputException, NotFoundException {
        appUserValidator.validateId(id);
        var appUser = appUserRepository.findById(id);
        resultValidator.verifyNotNull(appUser, id);
        return appUserMapper.toDTO(appUser);
    }

    public AppUserDTO getUserByToken(String token) throws NotFoundException {
        String nickname = jwtUtils.extractNickname(token);
        var appUser = appUserRepository.findByNickname(nickname);
        resultValidator.verifyNotNull(appUser, token);
        return appUserMapper.toDTO(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByNickname(username);
    }

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        appUserRepository.deleteById(id);
    }
}
