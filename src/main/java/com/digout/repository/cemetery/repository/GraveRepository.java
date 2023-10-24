package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Grave;
import com.digout.repository.cemetery.model.Localization;
import java.util.Collections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GraveRepository {

    public List<Grave> findAll() {
        return Collections.emptyList();
    }

    public Grave findById(int id) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public boolean update(int id) {
        return false;
    }

    public Grave add(String cemetery, String type, Localization localization,
                     String graveOwner, boolean availability){
        return null;
    }
}
