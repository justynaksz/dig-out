package com.digout.web.cemetery.controller;

import com.digout.repository.cemetery.repository.GraveRepository;
import com.digout.repository.cemetery.repository.LocalizationRepository;

public class GraveController {

    private final GraveRepository graveRepository;
    private final LocalizationRepository localizationRepository;

    public GraveController(GraveRepository graveRepository, LocalizationRepository localizationRepository) {
        this.graveRepository = graveRepository;
        this.localizationRepository = localizationRepository;
    }
}
