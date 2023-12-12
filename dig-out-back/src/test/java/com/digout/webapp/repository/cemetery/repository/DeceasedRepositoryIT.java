package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.cemetery.model.Deceased;
import com.digout.webapp.repository.cemetery.model.Grave;
import com.digout.webapp.repository.cemetery.model.Localization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        var localization1 = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var localization2 = new Localization(2, "Rest in peace cemetery", "J8", "C", "1");
        var localization3 = new Localization(3, "New moon cemetery", "B4", "4", "A");
        var localization4 = new Localization(4, "Oak valley cemetery", "A1", "14", "21");
        var localization5 = new Localization(5, "Green hills cemetery", "A0", "11", "3");
        var grave1 = new Grave(1, "coffin grave", localization4,
                "John Smith");
        var grave2 = new Grave(2, "coffin grave", localization1,
                "Edith Gawronsky");
        var grave3 = new Grave(3, "urn grave", localization3,
                "Mary Goldfin");
        var grave4 = new Grave(4, "columbarium", localization5,
                "Ann Black");
        var grave5 = new Grave(5, "urn grave", localization2,
                "Sean Williams");
        var deceased1 = new Deceased(1, "Noah", "Gross", LocalDate.of(1974, 11, 1),
                LocalDate.of(2023, 6, 13), false, grave5);
        var deceased2 = new Deceased(2, "Melody", "Fletcher", LocalDate.of(1942, 7, 29),
                LocalDate.of(2004, 1, 16), false, grave4);
        var deceased3 = new Deceased(3, "Vera", "Park", LocalDate.of(1991, 7, 1),
                LocalDate.of(1999, 2, 28), true, grave2);
        var deceased4 = new Deceased(4, "Adam", "Norris", LocalDate.of(1978, 11, 1),
                LocalDate.of(2019, 12, 17), false, grave1);
        var deceased5 = new Deceased(5, "Collin", "Moody", LocalDate.of(1964, 4, 19),
                LocalDate.of(2023, 1, 7), false, grave3);
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
            var localization = new Localization(3, "New moon cemetery", "B4", "4", "A");
            var grave = new Grave(3, "urn grave", localization,
                    "Mary Goldfin");
            var deceased = new Deceased(id, "Collin", "Moody", LocalDate.of(1964, 4, 19),
                    LocalDate.of(2023, 1, 7), false, grave);
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
            var localization = new Localization(4, "Oak valley cemetery", "A1", "14", "21");
            var grave = new Grave(1, "coffin grave", localization,
                    "John Smith");
            var deceased = new Deceased(id, "Peter", "Jones", LocalDate.of(1952, 10, 17),
                    LocalDate.of(1998, 4, 7), false, grave);
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
            var localization = new Localization(4, "New moon cemetery", "A1", "14", "21");
            var grave = new Grave(1, "coffin grave", localization,
                    "John Smith");
            var deceased = new Deceased(id, "Peter", "Jones", LocalDate.of(1952, 10, 17),
                    LocalDate.of(1998, 4, 7), false, grave);
            // WHEN
            deceasedRepository.save(deceased);
            // THEN
            assertEquals(6, deceasedRepository.findAll().size());
        }
    }
}
