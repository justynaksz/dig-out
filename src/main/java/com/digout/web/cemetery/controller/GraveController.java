package com.digout.web.cemetery.controller;

import com.digout.repository.cemetery.model.Grave;
import com.digout.repository.cemetery.repository.GraveRepository;
import com.digout.repository.cemetery.repository.LocalizationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/graves")
public class GraveController {

    private final GraveRepository graveRepository;
    private final LocalizationRepository localizationRepository;

    public GraveController(GraveRepository graveRepository, LocalizationRepository localizationRepository) {
        this.graveRepository = graveRepository;
        this.localizationRepository = localizationRepository;
    }

    @GetMapping
    public List<Grave> findAll() {
        return graveRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Grave findById(int id) {
        return graveRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(int id) {
        graveRepository.delete(id);
    }

    @PutMapping
    public Grave update(@RequestBody Grave grave) {
        return graveRepository.update(grave);
    }

    @PostMapping
    public Grave create(@RequestBody Grave grave) {
        return graveRepository.create(grave);
    }
}
