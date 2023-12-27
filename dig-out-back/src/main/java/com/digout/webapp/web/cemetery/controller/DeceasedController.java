package com.digout.webapp.web.cemetery.controller;

import com.digout.webapp.repository.cemetery.model.Deceased;
import com.digout.webapp.repository.cemetery.repository.DeceasedRepository;
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
@RequestMapping(path = "/deceased")
public class DeceasedController {

    private final DeceasedRepository deceasedRepository;

    @Autowired
    public DeceasedController(DeceasedRepository deceasedRepository) {
        this.deceasedRepository = deceasedRepository;
    }

    @GetMapping(path = "/birthdate")
    public List<Deceased> findByBirthDateAnniversary() {
        return deceasedRepository.findByBirthDateAnniversary();
    }

    @GetMapping(path = "/deathdate")
    public List<Deceased> findByDeathDateAnniversary() {
        return deceasedRepository.findByDeathDateAnniversary();
    }

    @GetMapping(path = "/grave/{id}")
    public List<Deceased> findByGraveId(@PathVariable int id) {
        return deceasedRepository.findByGraveId(id);
    }

    @GetMapping(path = "/search")
    public List<Deceased> findByParams(@RequestBody Deceased deceased) {
        return deceasedRepository.findByParams(deceased);
    }

    @GetMapping
    public List<Deceased> findAll() {
        return deceasedRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Deceased findById(@PathVariable int id) {
        return deceasedRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        deceasedRepository.deleteById(id);
    }

    @PostMapping
    public Deceased create(@RequestBody Deceased deceased) {
        return deceasedRepository.save(deceased);
    }
}
