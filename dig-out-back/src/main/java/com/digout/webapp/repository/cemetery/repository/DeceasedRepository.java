package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.Deceased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeceasedRepository extends JpaRepository<Deceased, Integer> {

    Deceased findById(int id);

    void deleteById(int id);

    Deceased save(Deceased deceased);
}
