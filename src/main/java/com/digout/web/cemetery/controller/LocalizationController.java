package com.digout.web.cemetery.controller;

import com.digout.repository.cemetery.model.Localization;
import com.digout.repository.cemetery.repository.LocalizationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocalizationController {

    private final LocalizationRepository localizationRepository;

    public LocalizationController(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    @GetMapping(path = "/localizations")
    public List<Localization> findAll() {
        return localizationRepository.findAll();
    }

    public boolean delete(int id) {
        return localizationRepository.delete(id);
    }

    public boolean update(int id) {
        return localizationRepository.update(id);
    }

    public Localization add(String quarter, String column, String row) {
        return localizationRepository.add(quarter, column, row);
    }
}
