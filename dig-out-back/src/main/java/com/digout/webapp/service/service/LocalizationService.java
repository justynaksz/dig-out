package com.digout.webapp.service.service;

import com.digout.webapp.repository.model.Localization;
import com.digout.webapp.repository.repository.LocalizationRepository;
import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.mapper.LocalizationMapper;
import com.digout.webapp.service.validator.LocalizationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalizationService {

    private final LocalizationRepository localizationRepository;
    private final LocalizationMapper localizationMapper;
    private final LocalizationValidator localizationValidator;

    private final String LOCALIZATION = "LOCALIZATION";

    @Autowired
    public LocalizationService(LocalizationRepository localizationRepository,
                               LocalizationMapper localizationMapper,
                               LocalizationValidator localizationValidator) {
        this.localizationRepository = localizationRepository;
        this.localizationMapper = localizationMapper;
        this.localizationValidator = localizationValidator;
    }

    public List<String> getCemeteriesList() throws EmptyResultException {
        var cemeteriesList = localizationRepository.getCemeteriesList();
        if (cemeteriesList.isEmpty()) {
            String CEMETERY = "CEMETERY";
            throw new EmptyResultException(CEMETERY);
        }
        return cemeteriesList;
    }

    public List<LocalizationDTO> getAll() throws EmptyResultException {
        var localizations = new ArrayList<LocalizationDTO>();
        for (Localization localization : localizationRepository.findAll()) {
            localizations.add(localizationMapper.toDTO(localization));
        }
        if (localizations.isEmpty()) {
            throw new EmptyResultException(LOCALIZATION);
        }
        return localizations;
    }

    public LocalizationDTO getById(int id) throws NotFoundException, InvalidInputException {
        localizationValidator.validateId(id);
        var result = localizationMapper.toDTO(localizationRepository.findById(id));
        if (result == null) {
            throw new NotFoundException(LOCALIZATION, id);
        }
        return result;
    }

    public void delete(int id) throws NotFoundException, InvalidInputException {
        getById(id);
        localizationRepository.deleteById(id);
    }

    public LocalizationDTO save(LocalizationDTO localizationDTO) {
        var localization = localizationMapper.toModel(localizationDTO);
        return localizationMapper.toDTO(localizationRepository.save(localization));
    }
}
