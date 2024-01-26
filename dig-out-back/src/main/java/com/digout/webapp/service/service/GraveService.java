package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.Grave;
import com.digout.webapp.repository.repository.GraveRepository;
import com.digout.webapp.service.DTO.GraveDTO;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.GraveMapper;
import com.digout.webapp.service.mapper.GraveOwnerMapper;
import com.digout.webapp.service.validator.GraveValidator;
import com.digout.webapp.service.validator.ResultValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraveService {

    private final GraveRepository graveRepository;
    private final GraveMapper graveMapper;
    private final GraveOwnerMapper graveOwnerMapper;
    private final GraveValidator graveValidator;
    private final ResultValidator<Grave> resultValidator;

    @Autowired

    public GraveService(GraveRepository graveRepository,
                        GraveMapper graveMapper,
                        GraveOwnerMapper graveOwnerMapper,
                        GraveValidator graveValidator,
                        ResultValidator<Grave> resultValidator) {
        this.graveRepository = graveRepository;
        this.graveMapper = graveMapper;
        this.graveOwnerMapper = graveOwnerMapper;
        this.graveValidator = graveValidator;
        this.resultValidator = resultValidator;
    }

    public List<GraveDTO> getByGraveOwner(GraveOwnerDTO graveOwnerDTO) throws EmptyResultException,
            InvalidInputException, EmptyFieldException {
        var graveOwner = graveOwnerMapper.toModel(graveOwnerDTO);
        var graves = graveRepository.findByGraveOwner(graveOwner);
        resultValidator.verifyNotNullOrEmptyList(graves);
        return graveMapper.convertModelToDTO(graves);
    }

    public List<GraveDTO> getGravesWithoutOwnersByCemetery(String cemetery) throws EmptyResultException,
            InvalidInputException, EmptyFieldException {
        graveValidator.validateCemetery(cemetery);
        var graves = graveRepository.findAvailableByCemetery(cemetery);
        resultValidator.verifyNotNullOrEmptyList(graves);
        return graveMapper.convertModelToDTO(graves);
    }

    public List<GraveDTO> getGravesByParam(GraveDTO graveDTO) throws EmptyResultException {
        var grave = graveMapper.toModel(graveDTO);
        var graves = graveRepository.findByParams(grave);
        resultValidator.verifyNotNullOrEmptyList(graves);
        return graveMapper.convertModelToDTO(graves);
    }

    public List<GraveDTO> getAll() throws EmptyResultException {
        var graves = graveRepository.findAll();
        resultValidator.verifyNotNullOrEmptyList(graves);
        return graveMapper.convertModelToDTO(graves);
    }

    public GraveDTO getById(int id) throws InvalidInputException, NotFoundException {
        graveValidator.validateId(id);
        var grave =  graveRepository.findById(id);
        resultValidator.verifyNotNull(grave, id);
        return graveMapper.toDTO(grave);
    }

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        graveRepository.deleteById(id);
    }

    public GraveDTO save(GraveDTO graveDTO) throws InvalidInputException, EmptyFieldException {
        if (graveDTO.getId() == 0) {
            graveValidator.validateAvailabilityInNewGraves(graveDTO.isPlaceAvailable());
        }
        graveValidator.isValid(graveDTO);
        var grave = graveMapper.toModel(graveDTO);
        return graveMapper.toDTO(graveRepository.save(grave));
    }

    // TODO find graves with free spaces for new deceased, adding parameter to grave which calculates capacity
}
