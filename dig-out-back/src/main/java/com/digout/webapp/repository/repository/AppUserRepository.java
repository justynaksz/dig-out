package com.digout.webapp.repository.repository;

import com.digout.webapp.repository.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findById(int id);

    AppUser findByEmail(String email);

    AppUser findByNickname(String nickname);

    void deleteById(int id);

    AppUser save(AppUser appUser);
}
