package com.digout.webapp.repository.repository;

import com.digout.webapp.repository.model.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Integer> {

    @Query(value = "SELECT l.cemetery FROM Localization l GROUP BY l.cemetery")
    List<String> getCemeteriesList();
    Localization findById(int id);

    void deleteById(int id);

    Localization save(Localization localization);
}
