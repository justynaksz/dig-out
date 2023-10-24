package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Deceased;
import com.digout.repository.cemetery.model.Grave;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    public boolean delete(int id) {
        return false;
    }

    public boolean update(int id) {
        return false;
    }

    public Deceased add(String firstName, String lastName, LocalDateTime birthDate,
                        LocalDateTime deathDate, boolean isInfectiousDisease, Grave grave){
        return null;
    }
}
