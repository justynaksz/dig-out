package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.Grave;
import com.digout.webapp.repository.cemetery.model.GraveOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveRepository extends JpaRepository<Grave, Integer> {

    List<Grave> findByGraveOwner(GraveOwner graveOwner);

    Grave findById(int id);

    void deleteById(int id);

    Grave save(Grave grave);
}
