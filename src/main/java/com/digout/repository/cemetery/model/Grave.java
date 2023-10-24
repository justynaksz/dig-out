package com.digout.repository.cemetery.model;

public record Grave(
        int id,
        String cemetery,
        String type,
        Localization localization,
        String graveOwner,
        boolean availability) {
}
