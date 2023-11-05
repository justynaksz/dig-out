package com.digout.repository.cemetery.repository;

import com.digout.repository.cemetery.model.Deceased;
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

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
        var grave1 = new Grave(1, "New moon cemetery", "coffin deceased", localization4, 
                "John Smith", true);
        var grave2 = new Grave(2, "Rest in peace cemetery", "coffin deceased", localization1, 
                "Edith Gawronsky", false);
        var grave3 = new Grave(3, "New moon cemetery", "urn deceased", localization3, 
                "Mary Goldfin", false);
        var grave4 = new Grave(4, "Oak valley cemetery", "columbarium", localization5, 
                "Ann Black", false);
        var grave5 = new Grave(5, "Green hills cemetery", "urn deceased", localization2, 
                "Sean Williams", true);
        var deceased1 = new Deceased(1, "Noah", "Gross", Date.valueOf("1974-11-01"), 
                Date.valueOf("2023-06-13"), false, grave5);
        var deceased2 = new Deceased(2, "Melody", "Fletcher", Date.valueOf("1942-07-29"),
                Date.valueOf("2004-01-16"), false, grave4);
        var deceased3 = new Deceased(3, "Vera", "Park", Date.valueOf("1991-07-01"), 
                Date.valueOf("1999-02-28"), true, grave2);
        var deceased4 = new Deceased(4, "Adam", "Norris", Date.valueOf("1978-11-01"), 
                Date.valueOf("2019-12-17"), false, grave1);
        var deceased5 = new Deceased(5, "Collin", "Moody", Date.valueOf("1964-04-19"),
                Date.valueOf("2023-01-07"), false, grave3);
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
            var grave = new Grave(3, "New moon cemetery", "urn deceased", localization, 
                    "Mary Goldfin", false);
            var deceased = new Deceased(id, "Collin", "Moody", Date.valueOf("1964-04-19"),
                    Date.valueOf("2023-01-07"), false, grave);
            var deceasedRetrieved = deceasedRepository.findById(id);
            // THEN
            assertEquals(deceased, deceasedRetrieved);
        }

        @Test
        @DisplayName("deceased not found")
        void findByIdShouldThrowExceptionIfDeceasedDoesNotExist() {
            // GIVEN
            var id = 6;
            // WHEN

            // THEN
            assertThrows(NotFoundException.class, () -> deceasedRepository.findById(id));
        }
    }

    @Nested
    @DisplayName("DECEASED - delete tests")
    class deleteTest {
        @Test
        @DisplayName("deleted deceased is not present in db")
        void deleteShouldRemoveDeceasedOfGivenIdFromDb() {
            // GIVEN
            var id = 5;
            var deceased = deceasedRepository.findById(id);
            // WHEN
            deceasedRepository.delete(id);
            // THEN
            assertFalse(deceasedRepository.findAll().contains(deceased));
        }

        @Test
        @DisplayName("deceased count should be decremented")
        void deleteShouldDecrementDeceasedCountInDb() {
            // GIVEN
            var id = 5;
            var deceasedCountExpected = deceasedRepository.findAll().size() - 1;
            // WHEN
            deceasedRepository.delete(id);
            // THEN
            assertEquals(deceasedCountExpected, deceasedRepository.findAll().size());
        }

        @Test
        @DisplayName("deleting not existing deceased")
        void deleteNotExistingDeceasedShouldThrowException() {
            // GIVEN
            var id = 8;
            // WHEN

            // THEN
            assertThrows(NotFoundException.class, () -> deceasedRepository.delete(id));
        }
    }

    @Nested
    @DisplayName("DECEASED - update tests")
    class updateTest {
        @Test
        @DisplayName("updated deceased is not equal to the pre-updated one")
        void updateShouldVaryDeceasedFromPreUpdateDeceasedInDb() {
            // GIVEN
            var id = 5;
            var localization = new Localization(3, "B4", "1", "A");
            var grave = new Grave(3, "New moon cemetery", "coffin deceased", localization,
                    "Eddy Goldfin", false);
            var deceasedUpdate = new Deceased(id, "Collin", "Happy",
                    Date.valueOf("1964-04-19"), Date.valueOf("2023-01-07"),
                    false, grave);
            var deceased = deceasedRepository.findById(id);
            // WHEN
            deceasedRepository.update(deceasedUpdate);
            // THEN
            assertNotEquals(deceased, deceasedRepository.findById(id));
        }

        @Test
        @DisplayName("updated deceased is equal to the given deceased")
        void updateShouldCorrectlyUpdateDeceasedInDb() {
            // GIVEN
            var id = 5;
            var localization = new Localization(3, "B4", "1", "A");
            var grave = new Grave(3, "New moon cemetery", "coffin deceased", localization,
                    "Eddy Goldfin", false);
            var deceasedUpdate = new Deceased(id, "Collin", "Happy",
                    Date.valueOf("1964-04-19"), Date.valueOf("2023-01-07"),
                    false, grave);
            // WHEN
            deceasedRepository.update(deceasedUpdate);
            // THEN
            assertEquals(deceasedUpdate, deceasedRepository.findById(id));
        }

        @Test
        @DisplayName("deceased count should not be changed")
        void updateShouldNotChangeDeceasedCountInDb() {
            // GIVEN
            var id = 5;
            var localization = new Localization(3, "B4", "1", "A");
            var grave = new Grave(3, "New moon cemetery", "coffin deceased", localization,
                    "Eddy Goldfin", false);
            var deceasedUpdate = new Deceased(id, "Collin", "Happy",
                    Date.valueOf("1964-04-19"), Date.valueOf("2023-01-07"),
                    false, grave);
            var deceasedCount = deceasedRepository.findAll().size();
            // WHEN
            deceasedRepository.update(deceasedUpdate);
            // THEN
            assertEquals(deceasedCount, deceasedRepository.findAll().size());
        }

        @Test
        @DisplayName("updating not existing deceased")
        void updateNotExistingDeceasedShouldThrowException() {
            // GIVEN
            var id = 10;
            var localization = new Localization(3, "B4", "1", "A");
            var grave = new Grave(3, "New moon cemetery", "coffin deceased", localization,
                    "Eddy Goldfin", false);
            var deceasedUpdate = new Deceased(id, "Collin", "Happy",
                    Date.valueOf("1964-04-19"), Date.valueOf("2023-01-07"),
                    false, grave);
            // WHEN

            // THEN
            assertThrows(NotFoundException.class,
                    () -> deceasedRepository.update(deceasedUpdate));
        }
    }

    @Nested
    @DisplayName("DECEASED - create tests")
    class createTest {
        @Test
        @DisplayName("created deceased is present in db")
        void createShouldCreateNewRecordInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(id, "A6", "7", "X");
            var grave = new Grave(id, "Happy ever after cemetery", "columbarium", localization,
                    "Cecil Anderson", true);
            var deceased = new Deceased(id, "Peter", "Jones", Date.valueOf("1952-10-17"),
                    Date.valueOf("1998-04-07"), false, grave);
            // WHEN
            deceasedRepository.create(deceased);
            // THEN
            assertTrue(deceasedRepository.findAll().contains(deceased));
        }

        @Test
        @DisplayName("deceased count is incremented when new deceased is created")
        void createShouldIncrementDeceasedCountInDb() {
            // GIVEN
            int id = 6;
            var localization = new Localization(id, "A6", "7", "X");
            var grave = new Grave(id, "Happy ever after cemetery", "columbarium", localization,
                    "Cecil Anderson", true);
            var deceased = new Deceased(id, "Peter", "Jones", Date.valueOf("1952-10-17"),
                    Date.valueOf("1998-04-07"), false, grave);
            // WHEN
            deceasedRepository.create(deceased);
            // THEN
            assertEquals(6, deceasedRepository.findAll().size());
        }
    }
}
