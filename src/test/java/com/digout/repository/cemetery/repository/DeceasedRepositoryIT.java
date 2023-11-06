package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Deceased;
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

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
class DeceasedRepositoryIT {

    @Autowired
    private DeceasedRepository deceasedRepository;

    @Test
    @DisplayName("DECEASED - find all test")
    void findAllShouldReturnListOfAllDeceased() {
        // GIVEN
        var localization1 = new Localization(1, "A1", "7", "18");
        var localization2 = new Localization(2, "J8", "C", "1");
        var localization3 = new Localization(3, "B4", "4", "A");
        var localization4 = new Localization(4, "A1", "14", "21");
        var localization5 = new Localization(5, "A0", "11", "3");
        var grave1 = new Grave(1, "New moon cemetery", "coffin grave", localization4,
                "John Smith", true);
        var grave2 = new Grave(2, "Rest in peace cemetery", "coffin grave", localization1,
                "Edith Gawronsky", false);
        var grave3 = new Grave(3, "New moon cemetery", "urn grave", localization3,
                "Mary Goldfin", false);
        var grave4 = new Grave(4, "Oak valley cemetery", "columbarium", localization5, 
                "Ann Black", false);
        var grave5 = new Grave(5, "Green hills cemetery", "urn grave", localization2,
                "Sean Williams", true);
        var deceased1 = new Deceased(1, "Noah", "Gross", LocalDateTime.of(1974, 11, 1, 0, 0, 0),
                LocalDateTime.of(2023, 6, 13, 0, 0, 0), false, grave5);
        var deceased2 = new Deceased(2, "Melody", "Fletcher", LocalDateTime.of(1942, 7, 29, 0, 0, 0),
                LocalDateTime.of(2004, 1, 16, 0, 0, 0), false, grave4);
        var deceased3 = new Deceased(3, "Vera", "Park", LocalDateTime.of(1991, 7, 1, 0, 0, 0),
                LocalDateTime.of(1999, 2, 28, 0, 0, 0), true, grave2);
        var deceased4 = new Deceased(4, "Adam", "Norris", LocalDateTime.of(1978, 11, 1, 0, 0, 0),
                LocalDateTime.of(2019, 12, 17, 0, 0, 0), false, grave1);
        var deceased5 = new Deceased(5, "Collin", "Moody", LocalDateTime.of(1964, 4, 19, 0, 0, 0),
                LocalDateTime.of(2023, 1, 7, 0, 0, 0), false, grave3);
        var deceasedList = new ArrayList<>();
        deceasedList.add(deceased1);
        deceasedList.add(deceased2);
        deceasedList.add(deceased3);
        deceasedList.add(deceased4);
        deceasedList.add(deceased5);
        // WHEN
        var deceasedRetrievedList = deceasedRepository.findAll();
        // THEN
        assertEquals(deceasedList, deceasedRetrievedList);
    }

    @Nested
    @DisplayName("DECEASED - find by id tests")
    class findByIdTest {
        @Test
        @DisplayName("deceased correctly found")
        void findByIdShouldReturnCorrectDeceased() {
            // GIVEN
            var id = 5;
            // WHEN
            var localization = new Localization(3, "B4", "4", "A");
            var grave = new Grave(3, "New moon cemetery", "urn grave", localization,
                    "Mary Goldfin", false);
            var deceased = new Deceased(id, "Collin", "Moody", LocalDateTime.of(1964, 4, 19, 0, 0, 0),
                    LocalDateTime.of(2023, 1, 7, 0, 0, 0), false, grave);
            var deceasedRetrieved = deceasedRepository.findById(id);
            // THEN
            assertEquals(deceased, deceasedRetrieved);
        }
    }

    @Nested
    @DisplayName("DECEASED - delete tests")
    class deleteTest {
        @Test
        @Transactional
        @DisplayName("deleted deceased is not present in db")
        void deleteShouldRemoveDeceasedOfGivenIdFromDb() {
            // GIVEN
            var id = 5;
            var deceased = deceasedRepository.findById(id);
            // WHEN
            deceasedRepository.deleteById(id);
            // THEN
            assertFalse(deceasedRepository.findAll().contains(deceased));
        }

        @Test
        @Transactional
        @DisplayName("deceased count should be decremented")
        void deleteShouldDecrementDeceasedCountInDb() {
            // GIVEN
            var id = 5;
            var deceasedCountExpected = deceasedRepository.findAll().size() - 1;
            // WHEN
            deceasedRepository.deleteById(id);
            // THEN
            assertEquals(deceasedCountExpected, deceasedRepository.findAll().size());
        }
    }

    @Nested
    @DisplayName("DECEASED - create tests")
    class createTest {
        @Test
        @Transactional
        @DisplayName("created deceased is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(4, "A1", "14", "21");
            var grave = new Grave(1, "New moon cemetery", "coffin grave", localization,
                    "John Smith", true);
            var deceased = new Deceased(id, "Peter", "Jones", LocalDateTime.of(1952, 10, 17, 0, 0, 0),
                    LocalDateTime.of(1998, 4, 7, 0, 0, 0), false, grave);
            // WHEN
            deceasedRepository.save(deceased);
            // THEN
            assertTrue(deceasedRepository.findAll().contains(deceased));
        }

        @Test
        @Transactional
        @DisplayName("deceased count is incremented when new deceased is created")
        void createShouldIncrementDeceasedCountInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(4, "A1", "14", "21");
            var grave = new Grave(1, "New moon cemetery", "coffin grave", localization,
                    "John Smith", true);
            var deceased = new Deceased(id, "Peter", "Jones", LocalDateTime.of(1952, 10, 17, 0, 0, 0),
                    LocalDateTime.of(1998, 4, 7, 0, 0, 0), false, grave);
            // WHEN
            deceasedRepository.save(deceased);
            // THEN
            assertEquals(6, deceasedRepository.findAll().size());
        }
    }
}
