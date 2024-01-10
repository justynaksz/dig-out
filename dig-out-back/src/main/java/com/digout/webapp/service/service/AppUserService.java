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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final AppUserValidator appUserValidator;

    private final String APP_USER = "APP USER";

    @Autowired

    public AppUserService(AppUserRepository appUserRepository,
                          AppUserMapper appUserMapper,
                          AppUserValidator appUserValidator) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.appUserValidator = appUserValidator;
    }

    public List<AppUserDTO> getAll() throws EmptyResultException {
        var appUsers = new ArrayList<AppUserDTO>();
        for (AppUser appUser : appUserRepository.findAll()) {
            appUsers.add(appUserMapper.toDTO(appUser));
        }
        if(appUsers.isEmpty()) {
            throw new EmptyResultException(APP_USER);
        }
        return appUsers;
    }

    public AppUserDTO getById(int id) throws InvalidInputException, NotFoundException {
        appUserValidator.validateId(id);
        var result = appUserMapper.toDTO(appUserRepository.findById(id));
        if (result == null) {
            throw new NotFoundException(APP_USER, id);
        }
        return result;
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
