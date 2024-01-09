package com.digout.webapp.repository.cemetery.repository;

import com.digout.webapp.repository.model.Deceased;
import com.digout.webapp.repository.model.Grave;
import com.digout.webapp.repository.model.GraveOwner;
import com.digout.webapp.repository.model.Localization;
import com.digout.webapp.repository.repository.DeceasedRepository;
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
    @Transactional
    @DisplayName("DECEASED - find by birthday")
    void findByBirthdayShouldReturnDeceasedWithBirthdayOnCurrentDay() {
        // GIVEN
        LocalDate today = LocalDate.now();
        var localization = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var graveOwner = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
        var grave = new Grave(2, "coffin grave", localization, graveOwner, "123452023072512345", true);
        var deceasedWithBirthday = new Deceased(6, "Joseph", "Smith",
                LocalDate.of(1987, today.getMonth(), today.getDayOfMonth()),
                LocalDate.of(1999, 12, 31),
                false, grave, "12345275475412345");
        var deceasedWithBirthdayList = new ArrayList<>();
        deceasedWithBirthdayList.add(deceasedWithBirthday);
        // WHEN
        deceasedRepository.save(deceasedWithBirthday);
        var deceasedRetrieved = deceasedRepository.findByBirthDateAnniversary();
        // THEN
        assertEquals(deceasedWithBirthdayList, deceasedRetrieved);
    }

    @Test
    @Transactional
    @DisplayName("DECEASED - find by death date")
    void findByDeathDayShouldReturnDeceasedWithDeathAnniversaryOnCurrentDay() {
        // GIVEN
        LocalDate today = LocalDate.now();
        var localization = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var graveOwner = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
        var grave = new Grave(2, "coffin grave", localization, graveOwner, "123452023072512345", true);
        var deceasedWithDeathAnniversary = new Deceased(6, "Joseph", "Smith",
                LocalDate.of(1941, 12, 31),
                LocalDate.of(1987, today.getMonth(), today.getDayOfMonth()),
                false, grave, "12345275475412345");
        var deceasedWithDeathAnniversaryList = new ArrayList<>();
        deceasedWithDeathAnniversaryList.add(deceasedWithDeathAnniversary);
        // WHEN
        deceasedRepository.save(deceasedWithDeathAnniversary);
        var deceasedRetrieved = deceasedRepository.findByDeathDateAnniversary();
        // THEN
        assertEquals(deceasedWithDeathAnniversaryList, deceasedRetrieved);
    }

    @Test
    @DisplayName("DECEASED - find all test")
    void findAllShouldReturnListOfAllDeceased() {
        // GIVEN
        var localization1 = new Localization(1, "New moon cemetery", "A1", "7", "18");
        var localization2 = new Localization(2, "Rest in peace cemetery", "J8", "C", "1");
        var localization3 = new Localization(3, "New moon cemetery", "B4", "4", "A");
        var localization4 = new Localization(4, "Oak valley cemetery", "A1", "14", "21");
        var localization5 = new Localization(5, "Green hills cemetery", "A0", "11", "3");
        var graveOwner1 = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
        var graveOwner2 = new GraveOwner(2, "Amanda", "Mallow", "74081517695", "Light Street", "14C/7", "Evenstone", "25-486", "Great Britain", null);
        var graveOwner3 = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
        var graveOwner4 = new GraveOwner(4, "Susan", "Austin", "88121417864", "Viersene Strasse", "24A", "Viersen", "12-784", "Germany", "574-445-127");
        var graveOwner5 = new GraveOwner(5, "John", "Gross", null, "Niepodleglosci", "14B/1", "Wielowies", "24-576", "Poland", "697-485-127");
        var grave1 = new Grave(1, "coffin grave", localization4, graveOwner2, "123452023011812345", true);
        var grave2 = new Grave(2, "coffin grave", localization1, graveOwner3, "123452023072512345", true);
        var grave3 = new Grave(3, "urn grave", localization3, graveOwner1, "123452020011812345", true);
        var grave4 = new Grave(4, "columbarium", localization5, graveOwner5, null, true);
        var grave5 = new Grave(5, "urn grave", localization2, graveOwner4, "123452019110112345", true);
        var deceased1 = new Deceased(1, "Noah", "Gross", LocalDate.of(1974, 11, 1),
                LocalDate.of(2023, 6, 13), false, grave5, "1236364364512345");
        var deceased2 = new Deceased(2, "Melody", "Fletcher", LocalDate.of(1942, 7, 29),
                LocalDate.of(2004, 1, 16), false, grave4, "1234564564512345");
        var deceased3 = new Deceased(3, "Vera", "Park", LocalDate.of(1991, 7, 1),
                LocalDate.of(1999, 2, 28), true, grave2, "1234564643512345");
        var deceased4 = new Deceased(4, "Adam", "Norris", LocalDate.of(1978, 11, 1),
                LocalDate.of(2019, 12, 17), false, grave1, "123457656542512345");
        var deceased5 = new Deceased(5, "Collin", "Moody", LocalDate.of(1964, 4, 19),
                LocalDate.of(2023, 1, 7), false, grave3, "1234564565412345");
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
            var graveOwner = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
            var grave = new Grave(3, "urn grave", localization, graveOwner, "123452020011812345", true);
            var deceased = new Deceased(id, "Collin", "Moody", LocalDate.of(1964, 4, 19),
                    LocalDate.of(2023, 1, 7), false, grave, "1234564565412345");
            var deceasedRetrieved = deceasedRepository.findById(id);
            // THEN
            assertEquals(deceased, deceasedRetrieved);
        }
    }

    @Nested
    @DisplayName("DECEASED - find by params")
    class findByParams {
        @Test
        @Transactional
        @DisplayName("find by cemetery name")
        void findByCemeteryNameShouldReturnCorrectDeceased() {
            // GIVEN
            var cemetery = "New moon cemetery";
            // WHEN
            var localization1 = new Localization(3, "New moon cemetery", "B4", "4", "A");
            var graveOwner1 = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
            var grave1 = new Grave(3, "urn grave", localization1, graveOwner1, "123452020011812345", true);
            var deceased1 = new Deceased(5, "Collin", "Moody", LocalDate.of(1964, 4, 19),
                    LocalDate.of(2023, 1, 7), false, grave1, "1234564565412345");
            var localization2 = new Localization(1, "New moon cemetery", "A1", "7", "18");
            var graveOwner2 = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
            var grave2 = new Grave(2, "coffin grave", localization2, graveOwner2, "123452023072512345", true);
            var deceased2 = new Deceased(3, "Vera", "Park", LocalDate.of(1991, 7, 1),
                    LocalDate.of(1999, 2, 28), true, grave2, "1234564643512345");
            var deceasedByCemetery = new ArrayList<>();
            deceasedByCemetery.add(deceased1);
            deceasedByCemetery.add(deceased2);
            var localizationWithParam = new Localization(0, cemetery, null,
                    null, null);
            var graveWithParam = new Grave(0, null, localizationWithParam, null,
                    null, true);
            var deceasedWithParam = new Deceased(0, null, null, null, null,
                    false, graveWithParam, null);
            var deceasedRetrievedByCemetery = deceasedRepository.findByParams(deceasedWithParam);
            // THEN
            assertEquals(deceasedByCemetery.size(), deceasedRetrievedByCemetery.size());
            assertTrue(deceasedByCemetery.containsAll(deceasedRetrievedByCemetery));
            assertTrue(deceasedRetrievedByCemetery.containsAll(deceasedByCemetery));
        }

        @Test
        @Transactional
        @DisplayName("find by deceased name")
        void findByDeceasedNameShouldReturnCorrectDeceased() {
            // GIVEN
            var name = "Collin";
            var surname = "Moody";
            // WHEN
            var localization = new Localization(3, "New moon cemetery", "B4", "4", "A");
            var graveOwner = new GraveOwner(1, "Emily", "Blunt", "88121417864", "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain", "459-782-145");
            var grave = new Grave(3, "urn grave", localization, graveOwner, "123452020011812345", true);
            var deceased = new Deceased(5, "Collin", "Moody", LocalDate.of(1964, 4, 19),
                    LocalDate.of(2023, 1, 7), false, grave, "1234564565412345");
            var localizationWithParam = new Localization(0, null, null,
                    null, null);
            var graveWithParam = new Grave(0, null, localizationWithParam, null,
                    null, true);
            var deceasedByName = new Deceased(0, name, surname, null, null,
                    false, graveWithParam, null);
            var deceasedRetrievedByName = deceasedRepository.findByParams(deceasedByName);
            // THEN
            assertEquals(deceasedByName, deceasedByName);
        }

        @Test
        @Transactional
        @DisplayName("find by cemetery and deceased name")
        void findByCemeteryAndDeceasedNameShouldReturnCorrectDeceased() {
            // GIVEN
            var cemetery = "New moon cemetery";
            var name = "Vera";
            var surname = "Park";
            // WHEN
            var localization = new Localization(1, "New moon cemetery", "A1", "7", "18");
            var graveOwner = new GraveOwner(3, "Robert", "Watson", null, null, null, null, null, null, null);
            var grave = new Grave(2, "coffin grave", localization, graveOwner, "123452023072512345", true);
            var deceased = new Deceased(3, "Vera", "Park", LocalDate.of(1991, 7, 1),
                    LocalDate.of(1999, 2, 28), true, grave, "1234564643512345");
            var deceasedByCemetery = new ArrayList<>();
            deceasedByCemetery.add(deceased);
            var localizationWithParam = new Localization(0, cemetery, null,
                    null, null);
            var graveWithParam = new Grave(0, null, localizationWithParam, null,
                    null, true);
            var deceasedWithParam = new Deceased(0, name, surname, null, null,
                    false, graveWithParam, null);
            var deceasedRetrievedByCemetery = deceasedRepository.findByParams(deceasedWithParam);
            // THEN
            assertEquals(deceasedByCemetery.size(), deceasedRetrievedByCemetery.size());
            assertTrue(deceasedByCemetery.containsAll(deceasedRetrievedByCemetery));
            assertTrue(deceasedRetrievedByCemetery.containsAll(deceasedByCemetery));
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
            var graveOwner = new GraveOwner(1, "Ann", "Nowak", "64122917574", "Main Street", "187", "London", "17-458", "Great Britain", "478-845-425");
            var grave = new Grave(id, "columbarium", localization,
                    graveOwner, "123452572411812345", true);
            var deceased = new Deceased(id, "Peter", "Jones", LocalDate.of(1952, 10, 17),
                    LocalDate.of(1998, 4, 7), false, grave, "123452576551812345");
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
            var graveOwner = new GraveOwner(1, "Ann", "Nowak", "64122917574", "Main Street", "187", "London", "17-458", "Great Britain", "478-845-425");
            var grave = new Grave(id, "columbarium", localization,
                    graveOwner, "123452572411812345", false);
            var deceased = new Deceased(id, "Peter", "Jones", LocalDate.of(1952, 10, 17),
                    LocalDate.of(1998, 4, 7), false, grave, "123452576551812345");
            // WHEN
            deceasedRepository.save(deceased);
            // THEN
            assertEquals(6, deceasedRepository.findAll().size());
        }
    }
}
