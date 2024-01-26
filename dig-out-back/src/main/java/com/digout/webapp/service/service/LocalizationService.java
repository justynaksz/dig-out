package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.Localization;
import com.digout.webapp.repository.repository.LocalizationRepository;
import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.LocalizationMapper;
import com.digout.webapp.service.validator.LocalizationValidator;
import com.digout.webapp.service.validator.ResultValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalizationService {

    private final LocalizationRepository localizationRepository;
    private final LocalizationMapper localizationMapper;
    private final LocalizationValidator localizationValidator;
    private final ResultValidator<Localization> resultValidator;

    @Autowired
    public LocalizationService(LocalizationRepository localizationRepository,
                               LocalizationMapper localizationMapper,
                               LocalizationValidator localizationValidator,
                               ResultValidator<Localization> resultValidator) {
        this.localizationRepository = localizationRepository;
        this.localizationMapper = localizationMapper;
        this.localizationValidator = localizationValidator;
        this.resultValidator = resultValidator;
    }

    public List<String> getCemeteriesList() throws EmptyResultException {
        var cemeteriesList = localizationRepository.getCemeteriesList();
        if (cemeteriesList.isEmpty()) {
            final String CEMETERY = "CEMETERY";
            throw new EmptyResultException(CEMETERY);
        }
        return cemeteriesList;
    }

    public List<LocalizationDTO> getAll() throws EmptyResultException {
        var localizations = localizationRepository.findAll();
        resultValidator.verifyNotNullOrEmptyList(localizations);
        var localizationDTOs = new ArrayList<LocalizationDTO>();
        for (Localization localization : localizations) {
            localizationDTOs.add(localizationMapper.toDTO(localization));
        }
        return localizationDTOs;
    }

    public LocalizationDTO getById(int id) throws NotFoundException, InvalidInputException {
        localizationValidator.validateId(id);
        var localization = localizationRepository.findById(id);
        resultValidator.verifyNotNull(localization, id);
        return localizationMapper.toDTO(localization);
    }

    public void delete(int id) throws NotFoundException, InvalidInputException {
        getById(id);
        localizationRepository.deleteById(id);
    }

    public LocalizationDTO save(LocalizationDTO localizationDTO) throws InvalidInputException, EmptyFieldException {
        localizationValidator.isValid(localizationDTO);
        var localization = localizationMapper.toModel(localizationDTO);
        return localizationMapper.toDTO(localizationRepository.save(localization));
    }
}
