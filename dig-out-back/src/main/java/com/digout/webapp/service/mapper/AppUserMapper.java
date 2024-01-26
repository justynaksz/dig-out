package com.digout.webapp.service.mapper;

import com.digout.webapp.repository.model.AppUser;
import com.digout.webapp.service.DTO.AppUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper extends Mapper<AppUser, AppUserDTO> {

    private final GraveOwnerMapper graveOwnerMapper;

    @Autowired
    public AppUserMapper(GraveOwnerMapper graveOwnerMapper) {
        this.graveOwnerMapper = graveOwnerMapper;
    }

    @Override
    public AppUser toModel(AppUserDTO appUserDTO) {
        var id = appUserDTO.getId();
        var nickname = appUserDTO.getNickname();
        var password = appUserDTO.getPassword();
        var email = appUserDTO.getEmail();
        var role = appUserDTO.getRole();
        var graveOwner = graveOwnerMapper.toModel(appUserDTO.getGraveOwner());
        var photo = appUserDTO.getPhoto();
        return new AppUser(id, nickname, password, email, role, graveOwner, photo);
    }

    @Override
    public AppUserDTO toDTO(AppUser appUser) {
        var id = appUser.getId();
        var nickname = appUser.getNickname();
        var password = appUser.getPassword();
        var email = appUser.getEmail();
        var role = appUser.getRole();
        var graveOwner = graveOwnerMapper.toDTO(appUser.getGraveOwner());
        var photo = appUser.getPhoto();
        return new AppUserDTO(id, nickname, password, email, role, graveOwner, photo);
    }
}
