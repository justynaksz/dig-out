package com.digout.web.cemetery.controller;

import com.digout.repository.cemetery.repository.DeceasedRepository;
import com.digout.repository.cemetery.repository.GraveRepository;

public class DeceasedController {

    private final DeceasedRepository deceasedRepository;
    private final GraveRepository graveRepository;

    public DeceasedController(DeceasedRepository deceasedRepository, GraveRepository graveRepository) {
        this.deceasedRepository = deceasedRepository;
        this.graveRepository = graveRepository;
    }
}
