package com.digout.webapp.web.controller;

import com.digout.webapp.repository.model.Grave;
import com.digout.webapp.repository.model.GraveOwner;
import com.digout.webapp.repository.repository.GraveRepository;
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
@RequestMapping(path = "/graves")
public class GraveController {

    private final GraveRepository graveRepository;

    @Autowired
    public GraveController(GraveRepository graveRepository) {
        this.graveRepository = graveRepository;
    }

    @GetMapping(path = "/owner")
    public List<Grave> findByGraveOwner(@RequestBody GraveOwner graveOwner) {
        return graveRepository.findByGraveOwner(graveOwner);
    }

    @GetMapping(path = "/available")
    public List<Grave> findAvailableByCemetery(@RequestBody String cemetery) {
        return graveRepository.findAvailableByCemetery(cemetery);
    }

    @GetMapping
    public List<Grave> findAll() {
        return graveRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Grave findById(@PathVariable int id) {
        return graveRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        graveRepository.deleteById(id);
    }

    @PostMapping
    public Grave create(@RequestBody Grave grave) {
        return graveRepository.save(grave);
    }
}
