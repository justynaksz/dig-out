package com.digout.repository.cemetery.model;

public record Localization(
        int id,
        String quarter,
        String column,
        String row
) {
}
