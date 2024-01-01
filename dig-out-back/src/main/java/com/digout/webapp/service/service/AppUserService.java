package com.digout.webapp.service.service;

import com.digout.webapp.repository.cemetery.model.AppUser;
import com.digout.webapp.repository.cemetery.repository.AppUserRepository;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.mapper.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Autowired

    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    public List<AppUserDTO> getAll() {
        var appUsers = new ArrayList<AppUserDTO>();
        for (AppUser appUser : appUserRepository.findAll()) {
            appUsers.add(appUserMapper.toDTO(appUser));
        }
        return appUsers;
    }

    public AppUserDTO getById(int id) {
        return appUserMapper.toDTO(appUserRepository.findById(id));
    }

    public void delete(int id) {
        appUserRepository.deleteById(id);
    }

    public AppUserDTO save(AppUserDTO appUserDTO) {
        var appUser = appUserMapper.toModel(appUserDTO);
        return appUserMapper.toDTO(appUserRepository.save(appUser));
    }
}
