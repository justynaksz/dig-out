package com.digout.webapp.service.mapper;

import com.digout.webapp.repository.cemetery.model.Grave;
import com.digout.webapp.service.DTO.GraveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraveMapper {

    private final LocalizationMapper localizationMapper;
    private final GraveOwnerMapper graveOwnerMapper;

    @Autowired
    public GraveMapper(LocalizationMapper localizationMapper, GraveOwnerMapper graveOwnerMapper) {
        this.localizationMapper = localizationMapper;
        this.graveOwnerMapper = graveOwnerMapper;
    }

    public Grave toModel(GraveDTO graveDTO) {
        var id = graveDTO.getId();
        var type = graveDTO.getType();
        var localization = localizationMapper.toModel(graveDTO.getLocalization());
        var graveOwner = graveOwnerMapper.toModel(graveDTO.getGraveOwner());
        var photo = graveDTO.getPhoto();
        return new Grave(id, type, localization, graveOwner, photo);
    }

    public GraveDTO toDTO(Grave grave) {
        var id = grave.getId();
        var type = grave.getType();
        var localization = localizationMapper.toDTO(grave.getLocalization());
        var graveOwner = graveOwnerMapper.toDTO(grave.getGraveOwner());
        var photo = grave.getPhoto();
        return new GraveDTO(id, type, localization, graveOwner, photo);
    }
}
