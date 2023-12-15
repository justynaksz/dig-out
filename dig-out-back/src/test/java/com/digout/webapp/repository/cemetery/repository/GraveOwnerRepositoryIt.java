package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.GraveOwner;
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
class GraveOwnerRepositoryIt {

    @Autowired
    private GraveOwnerRepository graveOwnerRepository;

    @Test
    @DisplayName("GRAVE OWNER - find all test")
    void findAllShouldReturnListOfAllGraveOwners() {
        // GIVEN
        var graveOwner1 = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
        var graveOwner2 = new GraveOwner(2, "Amanda", "Mallow", "74081517695", "Light Street", "14C/7", "Evenstone", "25-486", "Great Britain", null);
        var graveOwner3 = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
        var graveOwner4 = new GraveOwner(4, "Susan", "Austin", "88121417864", "Viersene Strasse", "24A", "Viersen", "12-784", "Germany", "574-445-127");
        var graveOwner5 = new GraveOwner(5, "John", "Gross", null, "Niepodleglosci", "14B/1", "Wielowies", "24-576", "Poland", "697-485-127");
        var graveOwnerList = new ArrayList<>();
        graveOwnerList.add(graveOwner1);
        graveOwnerList.add(graveOwner2);
        graveOwnerList.add(graveOwner3);
        graveOwnerList.add(graveOwner4);
        graveOwnerList.add(graveOwner5);
        // WHEN
        var graveOwnerRetrievedList = graveOwnerRepository.findAll();
        // THEN
        assertEquals(graveOwnerList, graveOwnerRetrievedList);
    }


    @Test
    @DisplayName("GRAVE OWNER - find by id tests")
    void findByIdShouldReturnCorrectDeceased() {
        // GIVEN
        var id = 5;
        // WHEN
        var graveOwner = new GraveOwner(5, "John", "Gross", null, "Niepodleglosci", "14B/1", "Wielowies", "24-576", "Poland", "697-485-127");
        var graveOwnerRetrieved = graveOwnerRepository.findById(id);
        // THEN
        assertEquals(graveOwner, graveOwnerRetrieved);
    }

    @Nested
    @DisplayName("GRAVE OWNER - delete tests")
    class deleteTest {
        @Test
        @Transactional
        @DisplayName("deleted grave owner is not present in db")
        void deleteShouldRemoveGraveOwnerOfGivenIdFromDb() {
            // GIVEN
            var id = 5;
            var graveOwner = graveOwnerRepository.findById(id);
            // WHEN
            graveOwnerRepository.deleteById(id);
            // THEN
            assertFalse(graveOwnerRepository.findAll().contains(graveOwner));
        }

        @Test
        @Transactional
        @DisplayName("grave owner count should be decremented")
        void deleteShouldDecrementGraveOwnerCountInDb() {
            // GIVEN
            var id = 5;
            var graveOwnerCountExpected = graveOwnerRepository.findAll().size() - 1;
            // WHEN
            graveOwnerRepository.deleteById(id);
            // THEN
            assertEquals(graveOwnerCountExpected, graveOwnerRepository.findAll().size());
        }
    }

    @Nested
    @DisplayName("GRAVE OWNER - create tests")
    class createTest {
        @Test
        @Transactional
        @DisplayName("created grave owner is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            int id = 6;
            var graveOwner = new GraveOwner(id, "Ann", "Nowak", "64122917574", "Main Street", "187", "London", "17-458", "Great Britain", "478-845-425");
            // WHEN
            graveOwnerRepository.save(graveOwner);
            // THEN
            assertTrue(graveOwnerRepository.findAll().contains(graveOwner));
        }

        @Test
        @Transactional
        @DisplayName("grave owners count is incremented when new grave owner is created")
        void createShouldIncrementGraveOwnersCountInDb() {
            // GIVEN
            int id = 6;
            var graveOwner = new GraveOwner(id, "Ann", "Nowak", "64122917574", "Main Street", "187", "London", "17-458", "Great Britain", "478-845-425");
            // WHEN
            graveOwnerRepository.save(graveOwner);
            // THEN
            assertEquals(6, graveOwnerRepository.findAll().size());
            assertEquals(6, graveOwnerRepository.findAll().size());
        }
    }
}

