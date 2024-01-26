package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.AppUser;
import com.digout.webapp.repository.repository.AppUserRepository;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.AppUserMapper;
import com.digout.webapp.service.validator.AppUserValidator;
import com.digout.webapp.service.validator.ResultValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final AppUserValidator appUserValidator;
    private final ResultValidator<AppUser> resultValidator;

    @Autowired

    public AppUserService(AppUserRepository appUserRepository,
                          AppUserMapper appUserMapper,
                          AppUserValidator appUserValidator,
                          ResultValidator<AppUser> resultValidator) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.appUserValidator = appUserValidator;
        this.resultValidator = resultValidator;
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

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        appUserRepository.deleteById(id);
    }

    public AppUserDTO save(AppUserDTO appUserDTO) throws InvalidInputException, EmptyFieldException {
        appUserValidator.isValid(appUserDTO);
        var appUser = appUserMapper.toModel(appUserDTO);
        return appUserMapper.toDTO(appUserRepository.save(appUser));
    }
}
