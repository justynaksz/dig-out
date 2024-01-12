package com.digout.webapp.repository.repository;

import com.digout.webapp.repository.model.GraveOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraveOwnerRepository extends JpaRepository<GraveOwner, Integer> {

    GraveOwner findById(int id);

    void deleteById(int id);

    GraveOwner save(GraveOwner graveOwner);
}