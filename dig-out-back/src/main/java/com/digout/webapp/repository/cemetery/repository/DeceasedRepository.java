package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.Deceased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeceasedRepository extends JpaRepository<Deceased, Integer> {

    Deceased findById(int id);

    @Query("SELECT d FROM Deceased d WHERE EXTRACT(DAY FROM d.birthDate) = EXTRACT(DAY FROM CURRENT_DATE) " +
            "AND EXTRACT(MONTH FROM d.birthDate) = EXTRACT(MONTH FROM CURRENT_DATE)")
    List<Deceased> findByBirthDateAnniversary();

    @Query("SELECT d FROM Deceased d WHERE EXTRACT(DAY FROM d.deathDate) = EXTRACT(DAY FROM CURRENT_DATE) " +
            "AND EXTRACT(MONTH FROM d.deathDate) = EXTRACT(MONTH FROM CURRENT_DATE)")
    List<Deceased> findByDeathDateAnniversary();

    List<Deceased> findByGraveId(int graveId);

    @Query("SELECT d FROM Deceased d WHERE (:#{#deceased.firstName} IS NULL OR d.firstName = :#{#deceased.firstName})" +
            "AND (:#{#deceased.lastName} IS NULL OR d.lastName = :#{#deceased.lastName}) " +
            "AND (cast(cast(:#{#deceased.birthDate} as string) as date) IS NULL OR d.birthDate = :#{#deceased.birthDate}) " +
            "AND (cast(cast(:#{#deceased.deathDate} as string) as date) IS NULL OR d.deathDate = :#{#deceased.deathDate}) " +
            "AND (:#{#deceased.grave.type} IS NULL OR d.grave.type = :#{#deceased.grave.type})" +
            "AND (:#{#deceased.grave.localization.cemetery} IS NULL OR d.grave.localization.cemetery = :#{#deceased.grave.localization.cemetery})" +
            "AND (:#{#deceased.grave.localization.quarter} IS NULL OR d.grave.localization.quarter = :#{#deceased.grave.localization.quarter})" +
            "AND (:#{#deceased.grave.localization.localizationRow} IS NULL OR d.grave.localization.localizationRow = :#{#deceased.grave.localization.localizationRow})" +
            "AND (:#{#deceased.grave.localization.localizationColumn} IS NULL OR d.grave.localization.localizationColumn = :#{#deceased.grave.localization.localizationColumn})")
    List<Deceased> findByParams(@Param("deceased") Deceased deceased);

    void deleteById(int id);

    Deceased save(Deceased deceased);
}
