package com.digout.webapp.repository.repository;

import com.digout.webapp.repository.model.Grave;
import com.digout.webapp.repository.model.GraveOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveRepository extends JpaRepository<Grave, Integer> {

    List<Grave> findByGraveOwner(GraveOwner graveOwner);

    @Query(value = "SELECT g FROM Grave g WHERE g.localization.cemetery = :#{#cemetery} " +
            "AND g.graveOwner IS NULL " +
            "AND g.isPlaceAvailable = true")
    List<Grave> findAvailableByCemetery(String cemetery);

    @Query(value = "SELECT g FROM Grave g WHERE (:#{#grave.type} IS NULL OR g.type = :#{#grave.type})" +
            "AND (:#{#grave.localization.cemetery} IS NULL OR g.localization.cemetery = :#{#grave.localization.cemetery})" +
            "AND (:#{#grave.localization.quarter} IS NULL OR g.localization.quarter = :#{#grave.localization.quarter})" +
            "AND (:#{#grave.localization.localizationRow} IS NULL OR g.localization.localizationRow = :#{#grave.localization.localizationRow})" +
            "AND (:#{#grave.localization.localizationColumn} IS NULL OR g.localization.localizationColumn = :#{#grave.localization.localizationColumn})" +
            "AND (:#{#grave.placeAvailable} IS NULL OR g.isPlaceAvailable = :#{#grave.placeAvailable})")
    List<Grave> findByParams(@Param("grave") Grave grave);

    Grave findById(int id);

    void deleteById(int id);

    Grave save(Grave grave);
}
