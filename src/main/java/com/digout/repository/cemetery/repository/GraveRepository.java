package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Grave;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class GraveRepository {

    public List<Grave> findAll() {
        return Collections.emptyList();
    }

    public Grave findById(int id) {
        return null;
    }

    public void delete(int id) {
    }

    public Grave update(Grave grave) {
        return grave;
    }

    public Grave create(Grave grave) {
        return grave;
    }
}
