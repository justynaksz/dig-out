package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findById(int id);

    void deleteById(int id);

    AppUser save(AppUser appUser);
}
