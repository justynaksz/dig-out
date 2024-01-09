package com.digout.webapp.repository.repository;

import com.digout.webapp.repository.model.Grave;
import com.digout.webapp.repository.model.GraveOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveRepository extends JpaRepository<Grave, Integer> {

    List<Grave> findByGraveOwner(GraveOwner graveOwner);

    @Query(value = "SELECT g FROM Grave g WHERE g.localization.cemetery = :#{#cemetery} " +
            "AND g.graveOwner IS NULL " +
            "AND g.isPlaceAvailable = true")
    List<Grave> findAvailableByCemetery(String cemetery);

    Grave findById(int id);

    void deleteById(int id);

    Grave save(Grave grave);
}
