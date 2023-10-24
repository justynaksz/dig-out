package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Localization;
import java.util.Collections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalizationRepository {
    public List<Localization> findAll() {
        return Collections.emptyList();
    }

    public Localization findById(int id) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public boolean update(int id) {
        return false;
    }

    public Localization add(String quarter, String column, String row) {
        return null;
    }
}
