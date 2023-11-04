package com.digout.web.cemetery.controller;

import com.digout.repository.cemetery.model.Deceased;
import com.digout.repository.cemetery.repository.DeceasedRepository;
import com.digout.repository.cemetery.repository.GraveRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/deceased")
public class DeceasedController {

    private final DeceasedRepository deceasedRepository;
    private final GraveRepository graveRepository;

    public DeceasedController(DeceasedRepository deceasedRepository, GraveRepository graveRepository) {
        this.deceasedRepository = deceasedRepository;
        this.graveRepository = graveRepository;
    }

    @GetMapping
    public List<Deceased> findAll() {
        return deceasedRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Deceased findById(int id) {
        return deceasedRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(int id) {
        deceasedRepository.delete(id);
    }

    @PutMapping
    public Deceased update(@RequestBody Deceased deceased) {
        return deceasedRepository.update(deceased);
    }

    @PostMapping
    public Deceased create(@RequestBody Deceased deceased) {
        return deceasedRepository.create(deceased);
    }
}
