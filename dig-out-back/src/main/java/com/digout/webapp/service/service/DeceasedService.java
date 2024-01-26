package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.Deceased;
import com.digout.webapp.repository.repository.DeceasedRepository;
import com.digout.webapp.service.DTO.DeceasedDTO;
import com.digout.webapp.service.exeption.DeathBeforeBirthDateException;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.DeceasedMapper;
import com.digout.webapp.service.validator.DeceasedValidator;
import com.digout.webapp.service.validator.ResultValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeceasedService {

    private final DeceasedRepository deceasedRepository;
    private final DeceasedMapper deceasedMapper;
    private final DeceasedValidator deceasedValidator;

    private final ResultValidator<Deceased> resultValidator;

    @Autowired

    public DeceasedService(DeceasedRepository deceasedRepository,
                           DeceasedMapper deceasedMapper,
                           DeceasedValidator deceasedValidator,
                           ResultValidator<Deceased> resultValidator) {
        this.deceasedRepository = deceasedRepository;
        this.deceasedMapper = deceasedMapper;
        this.deceasedValidator = deceasedValidator;
        this.resultValidator = resultValidator;
    }

    public List<DeceasedDTO> getByBirthDateAnniversary() throws EmptyResultException {
        var deceasedList = deceasedRepository.findByBirthDateAnniversary();
        resultValidator.verifyNotNullOrEmptyList(deceasedList);
        return deceasedMapper.convertModelToDTO(deceasedList);
    }

    public List<DeceasedDTO> getByDeathDateAnniversary() throws EmptyResultException {
        var deceasedList = deceasedRepository.findByDeathDateAnniversary();
        resultValidator.verifyNotNullOrEmptyList(deceasedList);
        return deceasedMapper.convertModelToDTO(deceasedList);
    }

    public int getCountOfDeceasedInGrave(int graveId) throws InvalidInputException {
        deceasedValidator.validateId(graveId);
        var deceasedList = deceasedRepository.findByGraveId(graveId);
        if (deceasedList == null) {
            return 0;
        }
        return deceasedList.size();
    }

    public List<DeceasedDTO> getAll() throws EmptyResultException {
        var deceasedList = deceasedRepository.findAll();
        resultValidator.verifyNotNullOrEmptyList(deceasedList);
        return deceasedMapper.convertModelToDTO(deceasedList);
    }

    public DeceasedDTO getById(int id) throws InvalidInputException, NotFoundException {
        deceasedValidator.validateId(id);
        var deceased = deceasedRepository.findById(id);
        resultValidator.verifyNotNull(deceased, id);
        return deceasedMapper.toDTO(deceased);
    }

    public List<DeceasedDTO> getByParams(DeceasedDTO deceasedDTO) throws EmptyResultException,
            InvalidInputException, EmptyFieldException, DeathBeforeBirthDateException {
        var deceased = deceasedMapper.toModel(deceasedDTO);
        var deceasedList = deceasedRepository.findByParams(deceased);
        resultValidator.verifyNotNullOrEmptyList(deceasedList);
        return deceasedMapper.convertModelToDTO(deceasedList);
    }

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        deceasedRepository.deleteById(id);
    }

    public DeceasedDTO save(DeceasedDTO deceasedDTO) throws InvalidInputException, EmptyFieldException,
            DeathBeforeBirthDateException {
        deceasedValidator.isValid(deceasedDTO);
        var deceased = deceasedMapper.toModel(deceasedDTO);
        return deceasedMapper.toDTO(deceasedRepository.save(deceased));
    }
}
