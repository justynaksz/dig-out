package com.digout.repository.cemetery.model;

import java.time.LocalDateTime;

public record Deceased(
        int id,
        String firstName,
        String lastName,
        LocalDateTime birthDate,
        LocalDateTime deathDate,
        boolean isInfectiousDisease,
        Grave grave) {
}
