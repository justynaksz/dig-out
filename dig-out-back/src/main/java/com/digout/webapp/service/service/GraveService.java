package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.Grave;
import com.digout.webapp.repository.repository.GraveRepository;
import com.digout.webapp.service.DTO.GraveDTO;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.GraveMapper;
import com.digout.webapp.service.mapper.GraveOwnerMapper;
import com.digout.webapp.service.validator.GraveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraveService {

    private final GraveRepository graveRepository;
    private final GraveMapper graveMapper;
    private final GraveOwnerMapper graveOwnerMapper;

    private final GraveValidator graveValidator;

    private final String GRAVE = "GRAVE";

    @Autowired

    public GraveService(GraveRepository graveRepository,
                        GraveMapper graveMapper,
                        GraveOwnerMapper graveOwnerMapper,
                        GraveValidator graveValidator) {
        this.graveRepository = graveRepository;
        this.graveMapper = graveMapper;
        this.graveOwnerMapper = graveOwnerMapper;
        this.graveValidator = graveValidator;
    }

    // TODO validation of grave owner
    public List<GraveDTO> getByGraveOwner(GraveOwnerDTO graveOwnerDTO) throws EmptyResultException {
        var graveOwner = graveOwnerMapper.toModel(graveOwnerDTO);
        var graves = new ArrayList<GraveDTO>();
        for (Grave grave : graveRepository.findByGraveOwner(graveOwner)) {
            graves.add(graveMapper.toDTO(grave));
        }
        if (graves.isEmpty()) {
            throw new EmptyResultException(GRAVE);
        }
        return graves;
    }

    public List<GraveDTO> getAvailableByCemetery(String cemetery) throws EmptyResultException, InvalidInputException {
        graveValidator.validateCemetery(cemetery);
        var graves = new ArrayList<GraveDTO>();
        for (Grave grave : graveRepository.findAvailableByCemetery(cemetery)) {
            graves.add(graveMapper.toDTO(grave));
        }
        if (graves.isEmpty()) {
            throw new EmptyResultException(GRAVE);
        }
        return graves;
    }

    public List<GraveDTO> getAll() throws EmptyResultException {
        var graves = new ArrayList<GraveDTO>();
        for (Grave grave : graveRepository.findAll()) {
            graves.add(graveMapper.toDTO(grave));
        }
        if (graves.isEmpty()) {
            throw new EmptyResultException(GRAVE);
        }
        return graves;
    }

    public GraveDTO getById(int id) throws InvalidInputException, NotFoundException {
        graveValidator.validateId(id);
        var result =  graveMapper.toDTO(graveRepository.findById(id));
        if (result == null) {
            throw new NotFoundException(GRAVE, id);
        }
        return result;
    }

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        graveRepository.deleteById(id);
    }

    // TODO validate grave
    public GraveDTO save(GraveDTO graveDTO) {
        var grave = graveMapper.toModel(graveDTO);
        return graveMapper.toDTO(graveRepository.save(grave));
    }
}
