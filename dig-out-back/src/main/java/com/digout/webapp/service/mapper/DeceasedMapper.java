package com.digout.webapp.service.mapper;

import com.digout.webapp.repository.model.Deceased;
import com.digout.webapp.service.DTO.DeceasedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeceasedMapper {

    private final GraveMapper graveMapper;

    @Autowired
    public DeceasedMapper(GraveMapper graveMapper) {
        this.graveMapper = graveMapper;
    }

    public Deceased toModel(DeceasedDTO deceasedDTO) {
        var id = deceasedDTO.getId();
        var firstName = deceasedDTO.getFirstName();
        var lastName = deceasedDTO.getLastName();
        var birthDate = deceasedDTO.getBirthDate();
        var deathDate = deceasedDTO.getDeathDate();
        var isInfectiousDisease = deceasedDTO.isInfectiousDisease();
        var grave = graveMapper.toModel(deceasedDTO.getGrave());
        var photo = deceasedDTO.getPhoto();
        return new Deceased(id, firstName, lastName, birthDate, deathDate, isInfectiousDisease, grave, photo);
    }

    public DeceasedDTO toDTO(Deceased deceased) {
        var id = deceased.getId();
        var firstName = deceased.getFirstName();
        var lastName = deceased.getLastName();
        var birthDate = deceased.getBirthDate();
        var deathDate = deceased.getDeathDate();
        var isInfectiousDisease = deceased.isInfectiousDisease();
        var grave = graveMapper.toDTO(deceased.getGrave());
        var photo = deceased.getPhoto();
        return new DeceasedDTO(id, firstName, lastName, birthDate, deathDate, isInfectiousDisease, grave, photo);
    }
}
