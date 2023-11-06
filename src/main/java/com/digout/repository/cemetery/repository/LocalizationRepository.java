package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Integer> {

    Localization findById(int id);

    void deleteById(int id);

    Localization save(Localization localization);
}
