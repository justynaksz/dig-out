package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.GraveOwner;
import com.digout.webapp.repository.repository.GraveOwnerRepository;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.GraveOwnerMapper;
import com.digout.webapp.service.validator.GraveOwnerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraveOwnerService {

    private final GraveOwnerRepository graveOwnerRepository;
    private final GraveOwnerMapper graveOwnerMapper;
    private final GraveOwnerValidator graveOwnerValidator;

    private final String GRAVE_OWNER = "GRAVE OWNER";

    @Autowired
    public GraveOwnerService(GraveOwnerRepository graveOwnerRepository,
                             GraveOwnerMapper graveOwnerMapper,
                             GraveOwnerValidator graveOwnerValidator) {
        this.graveOwnerRepository = graveOwnerRepository;
        this.graveOwnerMapper = graveOwnerMapper;
        this.graveOwnerValidator = graveOwnerValidator;
    }

    public List<GraveOwnerDTO> getAll() throws EmptyResultException {
        var graveOwners = new ArrayList<GraveOwnerDTO>();
        for (GraveOwner graveOwner : graveOwnerRepository.findAll()) {
            graveOwners.add(graveOwnerMapper.toDTO(graveOwner));
        }
        if (graveOwners.isEmpty()) {
            throw new EmptyResultException(GRAVE_OWNER);
        }
        return graveOwners;
    }

    public GraveOwnerDTO getById(int id) throws InvalidInputException, NotFoundException {
        graveOwnerValidator.validateId(id);
        var result = graveOwnerMapper.toDTO(graveOwnerRepository.findById(id));
        if (result == null) {
            throw new NotFoundException(GRAVE_OWNER, id);
        }
        return result;
    }

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        graveOwnerRepository.deleteById(id);
    }

    // TODO validation of grave owner
    public GraveOwnerDTO save(GraveOwnerDTO graveOwnerDTO) {
        var graveOwner = graveOwnerMapper.toModel(graveOwnerDTO);
        return graveOwnerMapper.toDTO(graveOwnerRepository.save(graveOwner));
    }
}
