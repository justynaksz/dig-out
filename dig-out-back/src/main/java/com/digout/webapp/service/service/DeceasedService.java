package com.digout.webapp.service.service;

import com.digout.webapp.repository.cemetery.model.Deceased;
import com.digout.webapp.repository.cemetery.repository.DeceasedRepository;
import com.digout.webapp.service.DTO.DeceasedDTO;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.DeceasedMapper;
import com.digout.webapp.service.validator.DeceasedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeceasedService {

    private final DeceasedRepository deceasedRepository;
    private final DeceasedMapper deceasedMapper;
    private final DeceasedValidator deceasedValidator;

    private final String DECEASED = "DECEASED";

    @Autowired

    public DeceasedService(DeceasedRepository deceasedRepository,
                           DeceasedMapper deceasedMapper,
                           DeceasedValidator deceasedValidator) {
        this.deceasedRepository = deceasedRepository;
        this.deceasedMapper = deceasedMapper;
        this.deceasedValidator = deceasedValidator;
    }

    public List<DeceasedDTO> getByBirthDateAnniversary() throws EmptyResultException {
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceased : deceasedRepository.findByBirthDateAnniversary()) {
            deceasedList.add(deceasedMapper.toDTO(deceased));
        }
        if(deceasedList.isEmpty()) {
            throw new EmptyResultException(DECEASED);
        }
        return deceasedList;
    }

    public List<DeceasedDTO> getByDeathDateAnniversary() throws EmptyResultException {
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceased : deceasedRepository.findByDeathDateAnniversary()) {
            deceasedList.add(deceasedMapper.toDTO(deceased));
        }
        if(deceasedList.isEmpty()) {
            throw new EmptyResultException(DECEASED);
        }
        return deceasedList;
    }

    public List<DeceasedDTO> getAll() throws EmptyResultException {
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceased : deceasedRepository.findAll()) {
            deceasedList.add(deceasedMapper.toDTO(deceased));
        }
        if(deceasedList.isEmpty()) {
            throw new EmptyResultException(DECEASED);
        }
        return deceasedList;
    }

    public DeceasedDTO getById(int id) throws InvalidInputException, NotFoundException {
        deceasedValidator.validateId(id);
        var result = deceasedMapper.toDTO(deceasedRepository.findById(id));
        if (result == null) {
            throw new NotFoundException(DECEASED, id);
        }
        return result;
    }

    // TODO deceased validation
    public List<DeceasedDTO> getByParams(DeceasedDTO deceasedDTO) throws EmptyResultException {
        var deceased = deceasedMapper.toModel(deceasedDTO);
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceasedByParam : deceasedRepository.findByParams(deceased)) {
            deceasedList.add(deceasedMapper.toDTO(deceasedByParam));
        }
        if(deceasedList.isEmpty()) {
            throw new EmptyResultException(DECEASED);
        }
        return deceasedList;
    }

    public void delete(int id) throws InvalidInputException, NotFoundException {
        getById(id);
        deceasedRepository.deleteById(id);
    }

    // TODO deceased validation
    public DeceasedDTO save(DeceasedDTO deceasedDTO) {
        var deceased = deceasedMapper.toModel(deceasedDTO);
        return deceasedMapper.toDTO(deceasedRepository.save(deceased));
    }
}
