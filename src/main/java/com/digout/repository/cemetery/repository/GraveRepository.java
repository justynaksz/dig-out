package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Grave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraveRepository extends JpaRepository<Grave, Integer> {

    Grave findById(int id);

    void deleteById(int id);

    Grave save(Grave grave);
}
