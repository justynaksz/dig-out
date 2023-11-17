package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Grave;
import com.digout.repository.cemetery.model.Localization;
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
    @DisplayName("GRAVE - find all test")
    void findAllShouldReturnListOfAllGraves() {
        // GIVEN
        var localization1 = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var localization2 = new Localization(2, "Rest in peace cemetery", "J8", "C", "1");
        var localization3 = new Localization(3, "New moon cemetery", "B4", "4", "A");
        var localization4 = new Localization(4, "Oak valley cemetery", "A1", "14", "21");
        var localization5 = new Localization(5, "Green hills cemetery", "A0", "11", "3");
        var grave1 = new Grave(1, "coffin grave", localization4, "John Smith");
        var grave2 = new Grave(2, "coffin grave", localization1, "Edith Gawronsky");
        var grave3 = new Grave(3, "urn grave", localization3, "Mary Goldfin");
        var grave4 = new Grave(4, "columbarium", localization5, "Ann Black");
        var grave5 = new Grave(5, "urn grave", localization2, "Sean Williams");
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
            var grave = new Grave(3, "urn grave", localization, "Mary Goldfin");
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
            var grave = new Grave(id, "columbarium", localization,
                    "Cecil Anderson");
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
            var grave = new Grave(id, "columbarium", localization,
                    "Cecil Anderson");
            // WHEN
            graveRepository.save(grave);
            // THEN
            assertEquals(6, graveRepository.findAll().size());
        }
    }
}
