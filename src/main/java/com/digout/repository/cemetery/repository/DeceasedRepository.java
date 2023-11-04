package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Deceased;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class DeceasedRepository {

    public List<Deceased> findAll() {
        return Collections.emptyList();
    }

    public Deceased findById(int id) {
        return null;
    }

    public void delete(int id) {
    }

    public Deceased update(Deceased deceased) {
        return deceased;
    }

    public Deceased create(Deceased deceased) {
        return deceased;
    }
}
