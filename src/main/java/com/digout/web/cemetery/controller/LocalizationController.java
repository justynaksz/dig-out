package com.digout.web.cemetery.controller;

import com.digout.repository.cemetery.model.Localization;
import com.digout.repository.cemetery.repository.LocalizationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/localizations")
public class LocalizationController {

    private final LocalizationRepository localizationRepository;

    public LocalizationController(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    @GetMapping
    public List<Localization> findAll() {
        return localizationRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Localization findById(int id) {
        return localizationRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(int id) {
        localizationRepository.delete(id);
    }

    @PutMapping
    public Localization update(@RequestBody Localization localization) {
        return localizationRepository.update(localization);
    }

    @PostMapping
    public Localization create(@RequestBody Localization localization) {
        return localizationRepository.create(localization);
    }
}
