package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.GraveOwner;
import com.digout.webapp.repository.repository.GraveOwnerRepository;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.GraveOwnerMapper;
import com.digout.webapp.service.validator.GraveOwnerValidator;
import com.digout.webapp.service.validator.ResultValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraveOwnerService {

    private final GraveOwnerRepository graveOwnerRepository;
    private final GraveOwnerMapper graveOwnerMapper;
    private final GraveOwnerValidator graveOwnerValidator;
    private final ResultValidator<GraveOwner> resultValidator;

    @Autowired
    public GraveOwnerService(GraveOwnerRepository graveOwnerRepository,
                             GraveOwnerMapper graveOwnerMapper,
                             GraveOwnerValidator graveOwnerValidator,
                             ResultValidator<GraveOwner> resultValidator) {
        this.graveOwnerRepository = graveOwnerRepository;
        this.graveOwnerMapper = graveOwnerMapper;
        this.graveOwnerValidator = graveOwnerValidator;
        this.resultValidator = resultValidator;
    }

    public List<GraveOwnerDTO> getAll() throws EmptyResultException {
        var graveOwners = graveOwnerRepository.findAll();
        resultValidator.verifyNotNullOrEmptyList(graveOwners);
        return graveOwnerMapper.convertModelToDTO(graveOwners);
    }

    public List<GraveOwnerDTO> getGraveOwnersByParam(GraveOwnerDTO graveOwnerDTO) throws EmptyResultException {
        var graveOwner = graveOwnerMapper.toModel(graveOwnerDTO);
        var graveOwners = graveOwnerRepository.findGraveOwnerByParams(graveOwner);
        resultValidator.verifyNotNullOrEmptyList(graveOwners);
        return graveOwnerMapper.convertModelToDTO(graveOwners);
    }

    public GraveOwnerDTO getById(int id) throws InvalidInputException, NotFoundException {
        graveOwnerValidator.validateId(id);
        var graveOwner = graveOwnerRepository.findById(id);
        resultValidator.verifyNotNull(graveOwner, id);
        return graveOwnerMapper.toDTO(graveOwner);
    }

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        graveOwnerRepository.deleteById(id);
    }

    public GraveOwnerDTO save(GraveOwnerDTO graveOwnerDTO) throws InvalidInputException, EmptyFieldException {
        graveOwnerValidator.isValid(graveOwnerDTO);
        var graveOwner = graveOwnerMapper.toModel(graveOwnerDTO);
        return graveOwnerMapper.toDTO(graveOwnerRepository.save(graveOwner));
    }
}
