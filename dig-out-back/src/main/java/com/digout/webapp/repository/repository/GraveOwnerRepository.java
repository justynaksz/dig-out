package com.digout.webapp.repository.repository;

import com.digout.webapp.repository.model.GraveOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveOwnerRepository extends JpaRepository<GraveOwner, Integer> {

    GraveOwner findById(int id);

    @Query(value = "SELECT g FROM GraveOwner g WHERE (:#{#graveOwner.firstName} IS NULL OR g.firstName = :#{#graveOwner.firstName})" +
            "AND (:#{#graveOwner.lastName} IS NULL OR g.lastName = :#{#graveOwner.lastName})" +
            "AND (:#{#graveOwner.pesel} IS NULL OR g.pesel= :#{#graveOwner.pesel})")
    List<GraveOwner> findGraveOwnerByParams(@Param("graveOwner") GraveOwner graveOwner);

    void deleteById(int id);

    GraveOwner save(GraveOwner graveOwner);
}
