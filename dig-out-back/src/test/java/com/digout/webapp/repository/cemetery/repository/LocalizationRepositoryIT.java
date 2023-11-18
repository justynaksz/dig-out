package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.Localization;
import com.digout.webapp.repository.cemetery.repository.LocalizationRepository;
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
class LocalizationRepositoryIT {

    @Autowired
    private LocalizationRepository localizationRepository;

    @Test
    @DisplayName("LOCALIZATION - find all test")
    void findAllShouldReturnListOfAllLocalizations() {
        // GIVEN
        var localization1 = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var localization2 = new Localization(2, "Rest in peace cemetery", "J8", "C", "1");
        var localization3 = new Localization(3, "New moon cemetery", "B4", "4", "A");
        var localization4 = new Localization(4, "Oak valley cemetery", "A1", "14", "21");
        var localization5 = new Localization(5, "Green hills cemetery", "A0", "11", "3");
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
            var localization = new Localization(3, "New moon cemetery", "B4", "4", "A");
            var localizationRetrieved = localizationRepository.findById(3);
            // THEN
            assertEquals(localization, localizationRetrieved);
        }
    }

    @Nested
    @DisplayName("LOCALIZATION - delete tests")
    class deleteTest {
        @Test
        @Transactional
        @DisplayName("deleted localization is not present in db")
        void deleteShouldRemoveLocalizationOfGivenIdFromDb() {
            // GIVEN
            var id = 3;
            var localization = localizationRepository.findById(id);
            // WHEN
            localizationRepository.deleteById(id);
            // THEN
            assertFalse(localizationRepository.findAll().contains(localization));
        }

        @Test
        @Transactional
        @DisplayName("localization count should be decremented")
        void deleteShouldDecrementLocalizationsCountInDb() {
            // GIVEN
            var id = 1;
            var localizationsCountExpected = localizationRepository.findAll().size() - 1;
            // WHEN
            localizationRepository.deleteById(id);
            // THEN
            assertEquals(localizationsCountExpected, localizationRepository.findAll().size());
        }
    }

    @Nested
    @DisplayName("LOCALIZATION - create tests")
    class createTest {
        @Test
        @Transactional
        @DisplayName("created localization is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            var localization = new Localization(6, "New moon cemetery", "D9", "1", "1");
            // WHEN
            localizationRepository.save(localization);
            // THEN
            assertTrue(localizationRepository.findAll().contains(localization));
        }

        @Test
        @Transactional
        @DisplayName("localizations count is incremented when new localization is created")
        void createShouldIncrementLocalizationsCountInDb() {
            // GIVEN
            var localization = new Localization(6, "New moon cemetery", "D9", "1", "1");
            // WHEN
            localizationRepository.save(localization);
            // THEN
            assertEquals(6, localizationRepository.findAll().size());
        }
    }
}
