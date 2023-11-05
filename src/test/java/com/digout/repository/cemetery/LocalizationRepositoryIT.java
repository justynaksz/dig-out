package com.digout.repository.cemetery;

import com.digout.repository.cemetery.model.Localization;
import com.digout.repository.cemetery.repository.LocalizationRepository;
import com.digout.repository.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
class LocalizationRepositoryIT {

    @Autowired
    private LocalizationRepository localizationRepository;

    @Test
    @DisplayName("LOCALIZATION - find all test")
    void findAllShouldReturnListOfAllLocalizations() {
        // GIVEN
        var localization1 = new Localization(1, "A1", "7", "18");
        var localization2 = new Localization(2, "J8", "C", "1");
        var localization3 = new Localization(3, "B4", "4", "A");
        var localization4 = new Localization(4, "A1", "14", "21");
        var localization5 = new Localization(5, "A0", "11", "3");
        var localizations = new ArrayList<>();
        localizations.add(localization1);
        localizations.add(localization2);
        localizations.add(localization3);
        localizations.add(localization4);
        localizations.add(localization5);
        // WHEN
        var localizationsRetrieved = localizationRepository.findAll();
        // THEN
        assertEquals(localizations, localizationsRetrieved);
    }

    @Nested
    @DisplayName("LOCALIZATION - find by id tests")
    class findByIdTest {
        @Test
        @DisplayName("localization correctly found")
        void findByIdShouldReturnCorrectLocalization() {
            // GIVEN
            var id = 3;
            // WHEN
            var localization = new Localization(3, "B4", "4", "A");
            var localizationRetrieved = localizationRepository.findById(3);
            // THEN
            assertEquals(localization, localizationRetrieved);
        }

        @Test
        @DisplayName("localization not found")
        void findByIdShouldThrowExceptionIfLocalizationDoesNotExist() {
            // GIVEN
            var id = 6;
            // WHEN

            // THEN
            assertThrows(NotFoundException.class, () -> localizationRepository.findById(id));
        }
    }

    @Nested
    @DisplayName("LOCALIZATION - delete tests")
    class deleteTest {
        @Test
        @DisplayName("deleted localization is not present in db")
        void deleteShouldRemoveLocalizationOfGivenIdFromDb() {
            // GIVEN
            var id = 3;
            var localization = localizationRepository.findById(id);
            // WHEN
            localizationRepository.delete(id);
            // THEN
            assertFalse(localizationRepository.findAll().contains(localization));
        }

        @Test
        @DisplayName("localization count should be decremented")
        void deleteShouldDecrementLocalizationsCountInDb() {
            // GIVEN
            var id = 1;
            var localizationsCountExpected = localizationRepository.findAll().size() - 1;
            // WHEN
            localizationRepository.delete(id);
            // THEN
            assertEquals(localizationsCountExpected, localizationRepository.findAll().size());
        }

        @Test
        @DisplayName("deleting not existing localization")
        void deleteNotExistingLocalizationShouldThrowException() {
            // GIVEN
            var id = 8;
            // WHEN

            // THEN
            assertThrows(NotFoundException.class, () -> localizationRepository.delete(id));
        }
    }

    @Nested
    @DisplayName("LOCALIZATION - update tests")
    class updateTest {
        @Test
        @DisplayName("updated localization is not equal to the pre-updated one")
        void updateShouldVaryLocalizationFromPreUpdateLocalizationInDb() {
            // GIVEN
            var id = 3;
            var localization = localizationRepository.findById(id);
            var localizationUpdate = new Localization(id, "A8", "B", "7");
            // WHEN
            localizationRepository.update(localizationUpdate);
            // THEN
            assertNotEquals(localization, localizationRepository.findById(id));
        }

        @Test
        @DisplayName("updated localization is equal to the given localization")
        void updateShouldCorrectlyUpdateLocalizationInDb() {
            // GIVEN
            var id = 3;
            var localizationUpdate = new Localization(id, "A8", "B", "7");
            // WHEN
            localizationRepository.update(localizationUpdate);
            // THEN
            assertEquals(localizationUpdate, localizationRepository.findById(id));
        }

        @Test
        @DisplayName("localization count should not be changed")
        void updateShouldNotChangeLocalizationsCountInDb() {
            // GIVEN
            var id = 3;
            var localizationUpdate = new Localization(id, "A8", "B", "7");
            var localizationsCount = localizationRepository.findAll().size();
            // WHEN
            localizationRepository.update(localizationUpdate);
            // THEN
            assertEquals(localizationsCount, localizationRepository.findAll().size());
        }

        @Test
        @DisplayName("updating not existing localization")
        void updateNotExistingLocalizationShouldThrowException() {
            // GIVEN
            var id = 10;
            var localizationUpdate = new Localization(id, "A8", "B", "7");

            // WHEN

            // THEN
            assertThrows(NotFoundException.class,
                    () -> localizationRepository.update(localizationUpdate));
        }
    }

    @Nested
    @DisplayName("LOCALIZATION - create tests")
    class createTest {
        @Test
        @DisplayName("created localization is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            var localization = new Localization(6, "D9", "1", "1");
            // WHEN
            localizationRepository.create(localization);
            // THEN
            assertTrue(localizationRepository.findAll().contains(localization));
        }

        @Test
        @DisplayName("localizations count is incremented when new localization is created")
        void createShouldIncrementLocalizationsCountInDb() {
            // GIVEN
            var localization = new Localization(6, "D9", "1", "1");
            // WHEN
            localizationRepository.create(localization);
            // THEN
            assertEquals(6, localizationRepository.findAll().size());
        }
    }
}
