package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Localization;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class LocalizationRepository {

    public List<Localization> findAll() {
        return Collections.emptyList();
    }

    public Localization findById(int id) {
        return null;
    }

    public void delete(int id) {
    }

    public Localization update(Localization localization) {
        return localization;
    }

    public Localization create(Localization localization) {
        return localization;
    }
}
