package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.AppUser;
import com.digout.webapp.repository.cemetery.model.GraveOwner;
import com.digout.webapp.repository.cemetery.role.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
class AppUserRepositoryIT {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName("APP USER - find all test")
    void findAllShouldReturnListOfAllAppUsers() {
        // GIVEN
        var graveOwner1 = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
        var graveOwner4 = new GraveOwner(4, "Susan", "Austin", "88121417864", "Viersene Strasse", "24A", "Viersen", "12-784", "Germany", "574-445-127");
        var graveOwner5 = new GraveOwner(5, "John", "Gross", null, "Niepodleglosci", "14B/1", "Wielowies", "24-576", "Poland", "697-485-127");
        var appUser1 = new AppUser(1, "dbesciak", "aloha", "dbesciak@gmail.com", Role.USER, graveOwner4, null);
        var appUser2 = new AppUser(2, "administrator", "difficultPassword", "administrator@dig-out.com", Role.ADMIN, null, "123452011142512345");
        var appUser3 = new AppUser(3, "blondie85", "myNewPassword", "blondie85@onet.com", Role.USER, graveOwner1, "1234521178212345");
        var appUser4 = new AppUser(4, "johnGross", "littleJohnny", "john_gross@gmail.com", Role.USER, graveOwner5, "123452174612345");
        var appUser5 = new AppUser(5, "digger", "just_be_nice", "digger2023@yahoo.com", Role.USER, null, null);
        var appUsersList = new ArrayList<>();
        appUsersList.add(appUser1);
        appUsersList.add(appUser2);
        appUsersList.add(appUser3);
        appUsersList.add(appUser4);
        appUsersList.add(appUser5);
        // WHEN
        var appUsersRetrieved = appUserRepository.findAll();
        // THEN
        assertEquals(appUsersList, appUsersRetrieved);
    }

    @Test
    @DisplayName("APP USER - find by id tests")
    void findByIdShouldReturnCorrectAppUser() {
        // GIVEN
        var id = 5;
        // WHEN
        var appUser = new AppUser(5, "digger", "just_be_nice", "digger2023@yahoo.com", Role.USER, null, null);
        var appUserRetrieved = appUserRepository.findById(id);
        // THEN
        assertEquals(appUser, appUserRetrieved);
    }

    @Nested
    @DisplayName("APP USER - delete tests")
    class deleteTest {
        @Test
        @Transactional
        @DisplayName("deleted app user is not present in db")
        void deleteShouldRemoveAppUserOfGivenIdFromDb() {
            // GIVEN
            var id = 5;
            var appUser = appUserRepository.findById(id);
            // WHEN
            appUserRepository.deleteById(id);
            // THEN
            assertFalse(appUserRepository.findAll().contains(appUser));
        }

        @Test
        @Transactional
        @DisplayName("app users count should be decremented")
        void deleteShouldDecrementAppUsersCountInDb() {
            // GIVEN
            var id = 5;
            var appUsersCountExpected = appUserRepository.findAll().size() - 1;
            // WHEN
            appUserRepository.deleteById(id);
            // THEN
            assertEquals(appUsersCountExpected, appUserRepository.findAll().size());
        }
    }

    @Nested
    @DisplayName("APP USER - create tests")
    class createTest {
        @Test
        @Transactional
        @DisplayName("created app user is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            int id = 6;
            var graveOwner = new GraveOwner(4, "Susan", "Austin", "88121417864", "Viersene Strasse", "24A", "Viersen", "12-784", "Germany", "574-445-127");
            var appUser = new AppUser(id, "susanaustin14", "suzyTheSheep", "suzy_austin@gmail.com", Role.USER, graveOwner, "1235457812345");
            // WHEN
            appUserRepository.save(appUser);
            // THEN
            assertTrue(appUserRepository.findAll().contains(appUser));
        }

        @Test
        @Transactional
        @DisplayName("grave owners count is incremented when new grave owner is created")
        void createShouldIncrementGraveOwnersCountInDb() {
            // GIVEN
            int id = 6;
            var graveOwner = new GraveOwner(4, "Susan", "Austin", "88121417864", "Viersene Strasse", "24A", "Viersen", "12-784", "Germany", "574-445-127");
            var appUser = new AppUser(id, "susanaustin14", "suzyTheSheep", "suzy_austin@gmail.com", Role.USER, graveOwner, "1235457812345");
            // WHEN
            appUserRepository.save(appUser);
            // THEN
            assertEquals(6, appUserRepository.findAll().size());
        }
    }
}
