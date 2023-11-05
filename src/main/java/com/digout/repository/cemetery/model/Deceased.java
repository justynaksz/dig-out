package com.digout.repository.cemetery.model;

import java.util.Date;

public record Deceased(
        int id,
        String firstName,
        String lastName,
        Date birthDate,
        Date deathDate,
        boolean isInfectiousDisease,
        Grave grave) {
}
