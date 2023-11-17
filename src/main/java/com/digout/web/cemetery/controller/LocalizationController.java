package com.digout.web.cemetery.controller;

import com.digout.repository.cemetery.model.Localization;
import com.digout.repository.cemetery.repository.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/localizations")
public class LocalizationController {

    private final LocalizationRepository localizationRepository;

    @Autowired
    public LocalizationController(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    @GetMapping
    public List<Localization> findAll() {
        return localizationRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Localization findById(@PathVariable int id) {
        return localizationRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        localizationRepository.deleteById(id);
    }

    @PostMapping
    public Localization create(@RequestBody Localization localization) {
        return localizationRepository.save(localization);
    }
}
