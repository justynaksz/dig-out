package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Grave;
import com.digout.repository.cemetery.model.Localization;
import com.digout.repository.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GraveRepositoryIT {

    @Autowired
    private GraveRepository graveRepository;

    @Test
    @DisplayName("GRAVE - find all test")
    void findAllShouldReturnListOfAllGraves() {
        // GIVEN
        var localization1 = new Localization(1, "A1", "7", "18");
        var localization2 = new Localization(2, "J8", "C", "1");
        var localization3 = new Localization(3, "B4", "4", "A");
        var localization4 = new Localization(4, "A1", "14", "21");
        var localization5 = new Localization(5, "A0", "11", "3");
        var grave1 = new Grave(1, "New moon cemetery", "coffin grave", localization4, "John Smith", true);
        var grave2 = new Grave(2, "Rest in peace cemetery", "coffin grave", localization1, "Edith Gawronsky", false);
        var grave3 = new Grave(3, "New moon cemetery", "urn grave", localization3, "Mary Goldfin", false);
        var grave4 = new Grave(4, "Oak valley cemetery", "columbarium", localization5, "Ann Black", false);
        var grave5 = new Grave(5, "Green hills cemetery", "urn grave", localization2, "Sean Williams", true);
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
            var localization = new Localization(3, "B4", "4", "A");
            var grave = new Grave(3, "New moon cemetery", "urn grave", localization, "Mary Goldfin", false);
            var graveRetrieved = graveRepository.findById(3);
            // THEN
            assertEquals(grave, graveRetrieved);
        }

        @Test
        @DisplayName("grave not found")
        void findByIdShouldThrowExceptionIfGraveDoesNotExist() {
            // GIVEN
            var id = 6;
            // WHEN

            // THEN
            assertThrows(NotFoundException.class, () -> graveRepository.findById(id));
        }
    }

    @Nested
    @DisplayName("GRAVE - delete tests")
    class deleteTest {
        @Test
        @DisplayName("deleted grave is not present in db")
        void deleteShouldRemoveGraveOfGivenIdFromDb() {
            // GIVEN
            var id = 3;
            var grave = graveRepository.findById(id);
            // WHEN
            graveRepository.delete(id);
            // THEN
            assertFalse(graveRepository.findAll().contains(grave));
        }

        @Test
        @DisplayName("grave count should be decremented")
        void deleteShouldDecrementGravesCountInDb() {
            // GIVEN
            var id = 1;
            var gravesCountExpected = graveRepository.findAll().size() - 1;
            // WHEN
            graveRepository.delete(id);
            // THEN
            assertEquals(gravesCountExpected, graveRepository.findAll().size());
        }

        @Test
        @DisplayName("deleting not existing grave")
        void deleteNotExistingGraveShouldThrowException() {
            // GIVEN
            var id = 8;
            // WHEN

            // THEN
            assertThrows(NotFoundException.class, () -> graveRepository.delete(id));
        }
    }

    @Nested
    @DisplayName("GRAVE - update tests")
    class updateTest {
        @Test
        @DisplayName("updated grave is not equal to the pre-updated one")
        void updateShouldVaryGraveFromPreUpdateGraveInDb() {
            // GIVEN
            var id = 3;
            var localization = new Localization(3, "B4", "1", "A");
            var graveUpdate = new Grave(id, "New moon cemetery", "coffin grave", localization,
                    "Eddy Goldfin", false);
            var grave = graveRepository.findById(id);
            // WHEN
            graveRepository.update(graveUpdate);
            // THEN
            assertNotEquals(grave, graveRepository.findById(id));
        }

        @Test
        @DisplayName("updated grave is equal to the given grave")
        void updateShouldCorrectlyUpdateGraveInDb() {
            // GIVEN
            var id = 3;
            var localization = new Localization(3, "B4", "1", "A");
            var graveUpdate = new Grave(id, "New moon cemetery", "coffin grave", localization,
                    "Eddy Goldfin", false);
            // WHEN
            graveRepository.update(graveUpdate);
            // THEN
            assertEquals(graveUpdate, graveRepository.findById(id));
        }

        @Test
        @DisplayName("grave count should not be changed")
        void updateShouldNotChangeGravesCountInDb() {
            // GIVEN
            var id = 3;
            var localization = new Localization(3, "B4", "1", "A");
            var graveUpdate = new Grave(id, "New moon cemetery", "coffin grave", localization,
                    "Eddy Goldfin", false);
            var gravesCount = graveRepository.findAll().size();
            // WHEN
            graveRepository.update(graveUpdate);
            // THEN
            assertEquals(gravesCount, graveRepository.findAll().size());
        }

        @Test
        @DisplayName("updating not existing grave")
        void updateNotExistingGraveShouldThrowException() {
            // GIVEN
            var id = 10;
            var localization = new Localization(3, "B4", "1", "A");
            var graveUpdate = new Grave(id, "New moon cemetery", "coffin grave", localization,
                    "Eddy Goldfin", false);
            // WHEN

            // THEN
            assertThrows(NotFoundException.class,
                    () -> graveRepository.update(graveUpdate));
        }
    }

    @Nested
    @DisplayName("GRAVE - create tests")
    class createTest {
        @Test
        @DisplayName("created grave is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(id, "A6", "7", "X");
            var grave = new Grave(id, "Happy ever after cemetery", "columbarium", localization,
                    "Cecil Anderson", true);
            // WHEN
            graveRepository.create(grave);
            // THEN
            assertTrue(graveRepository.findAll().contains(grave));
        }

        @Test
        @DisplayName("graves count is incremented when new grave is created")
        void createShouldIncrementGravesCountInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(id, "A6", "7", "X");
            var grave = new Grave(id, "Happy ever after cemetery", "columbarium", localization,
                    "Cecil Anderson", true);
            // WHEN
            graveRepository.create(grave);
            // THEN
            assertEquals(6, graveRepository.findAll().size());
        }
    }
}
