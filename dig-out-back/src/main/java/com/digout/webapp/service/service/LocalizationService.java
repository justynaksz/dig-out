package com.digout.webapp.service.service;

import com.digout.webapp.repository.cemetery.model.Localization;
import com.digout.webapp.repository.cemetery.repository.LocalizationRepository;
import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.mapper.LocalizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalizationService {

    private final LocalizationRepository localizationRepository;
    private final LocalizationMapper localizationMapper;

    @Autowired
    public LocalizationService(LocalizationRepository localizationRepository, LocalizationMapper localizationMapper) {
        this.localizationRepository = localizationRepository;
        this.localizationMapper = localizationMapper;
    }

    public List<LocalizationDTO> getAll() {
        var localizations = new ArrayList<LocalizationDTO>();
        for (Localization localization : localizationRepository.findAll()) {
            localizations.add(localizationMapper.toDTO(localization));
        }
        return localizations;
    }

    public LocalizationDTO getById(int id) {
        return localizationMapper.toDTO(localizationRepository.findById(id));
    }

    public void delete(int id) {
        localizationRepository.deleteById(id);
    }

    public LocalizationDTO save(LocalizationDTO localizationDTO) {
        var localization = localizationMapper.toModel(localizationDTO);
        return localizationMapper.toDTO(localizationRepository.save(localization));
    }
}
