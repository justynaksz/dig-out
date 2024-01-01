package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.Grave;
import com.digout.webapp.repository.cemetery.model.GraveOwner;
import com.digout.webapp.repository.cemetery.model.Localization;
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
class GraveRepositoryIT {

    @Autowired
    private GraveRepository graveRepository;

    @Test
    @DisplayName("GRAVE - find by grave owner")
    void findByGraveOwnerShouldReturnCorrectGraves() {
        // GIVEN
        var graveOwner = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
        var localization = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var grave = new Grave(2, "coffin grave", localization, graveOwner, "123452023072512345", true);
        // WHEN
        var gravesExpected = new ArrayList<>();
        gravesExpected.add(grave);
        var gravesRetrieved = graveRepository.findByGraveOwner(graveOwner);
        // THEN
        assertEquals(gravesExpected, gravesRetrieved);
    }

    @Test
    @DisplayName("GRAVE - find available by cemetery")
    void findAvailableGravesShouldReturnGravesWithoutOwnerAndWithPlaceAvailableFlag() {
        // GIVEN
        var cemetery = "New moon cemetery";
        // WHEN
        var gravesRetrieved = graveRepository.findAvailableByCemetery(cemetery);
        // THEN
        assertEquals(new ArrayList<>(), gravesRetrieved);
    }

    @Test
    @DisplayName("GRAVE - find all test")
    void findAllShouldReturnListOfAllGraves() {
        // GIVEN
        var localization1 = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var localization2 = new Localization(2, "Rest in peace cemetery", "J8", "C", "1");
        var localization3 = new Localization(3, "New moon cemetery", "B4", "4", "A");
        var localization4 = new Localization(4, "Oak valley cemetery", "A1", "14", "21");
        var localization5 = new Localization(5, "Green hills cemetery", "A0", "11", "3");
        var graveOwner1 = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
        var graveOwner = new GraveOwner(2, "Amanda", "Mallow", "74081517695", "Light Street", "14C/7", "Evenstone", "25-486", "Great Britain", null);
        var graveOwner3 = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
        var graveOwner4 = new GraveOwner(4, "Susan", "Austin", "88121417864", "Viersene Strasse", "24A", "Viersen", "12-784", "Germany", "574-445-127");
        var graveOwner5 = new GraveOwner(5, "John", "Gross", null, "Niepodleglosci", "14B/1", "Wielowies", "24-576", "Poland", "697-485-127");
        var grave1 = new Grave(1, "coffin grave", localization4, graveOwner, "123452023011812345", true);
        var grave2 = new Grave(2, "coffin grave", localization1, graveOwner3, "123452023072512345", true);
        var grave3 = new Grave(3, "urn grave", localization3, graveOwner1, "123452020011812345", true);
        var grave4 = new Grave(4, "columbarium", localization5, graveOwner5, null, true);
        var grave5 = new Grave(5, "urn grave", localization2, graveOwner4, "123452019110112345", true);
        var graves = new ArrayList<>();
        graves.add(grave1);
        graves.add(grave2);
        graves.add(grave3);
        graves.add(grave4);
        graves.add(grave5);
        // WHEN
        var gravesRetrieved = graveRepository.findAll();
        // THEN
        assertEquals(graves, gravesRetrieved);
    }

    @Nested
    @DisplayName("GRAVE - find by id tests")
    class findByIdTest {
        @Test
        @DisplayName("grave correctly found")
        void findByIdShouldReturnCorrectGrave() {
            // GIVEN
            var id = 3;
            // WHEN
            var localization = new Localization(3, "New moon cemetery", "B4", "4", "A");
            var graveOwner = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
            var grave = new Grave(3, "urn grave", localization, graveOwner, "123452020011812345", true);
            var graveRetrieved = graveRepository.findById(3);
            // THEN
            assertEquals(grave, graveRetrieved);
        }
    }

    @Nested
    @DisplayName("GRAVE - delete tests")
    class deleteTest {
        @Test
        @Transactional
        @DisplayName("deleted grave is not present in db")
        void deleteShouldRemoveGraveOfGivenIdFromDb() {
            // GIVEN
            var id = 3;
            var grave = graveRepository.findById(id);
            // WHEN
            graveRepository.deleteById(id);
            // THEN
            assertFalse(graveRepository.findAll().contains(grave));
        }

        @Test
        @Transactional
        @DisplayName("grave count should be decremented")
        void deleteShouldDecrementGravesCountInDb() {
            // GIVEN
            var id = 1;
            var gravesCountExpected = graveRepository.findAll().size() - 1;
            // WHEN
            graveRepository.deleteById(id);
            // THEN
            assertEquals(gravesCountExpected, graveRepository.findAll().size());
        }
    }

    @Nested
    @DisplayName("GRAVE - create tests")
    class createTest {
        @Test
        @Transactional
        @DisplayName("created grave is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(id, "Happy ever after cemetery", "A6", "7", "X");
            var graveOwner = new GraveOwner(1, "Ann", "Nowak", "64122917574", "Main Street", "187", "London", "17-458", "Great Britain", "478-845-425");
            var grave = new Grave(id, "columbarium", localization, graveOwner, "123452572411812345", true);
            // WHEN
            graveRepository.save(grave);
            // THEN
            assertTrue(graveRepository.findAll().contains(grave));
        }

        @Test
        @Transactional
        @DisplayName("graves count is incremented when new grave is created")
        void createShouldIncrementGravesCountInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(id, "Happy ever after cemetery", "A6", "7", "X");
            var graveOwner = new GraveOwner(1, "Ann", "Nowak", "64122917574", "Main Street", "187", "London", "17-458", "Great Britain", "478-845-425");
            var grave = new Grave(id, "columbarium", localization, graveOwner, "123452572411812345", true);
            // WHEN
            graveRepository.save(grave);
            // THEN
            assertEquals(6, graveRepository.findAll().size());
        }
    }
}
