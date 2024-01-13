package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.GraveDTO;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraveValidatorTest {

    private final GraveValidator graveValidator = new GraveValidator();

    @Test
    @DisplayName("grave - valid")
    void whenInputValidGraveDoesNotThrowException() {
        // GIVEN
        var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", null, null,
                null, null, null, null, null);
        var localization = new LocalizationDTO(1, "New moon cemetery", "A1", "7",
                "18");
        var grave = new GraveDTO(2, "coffin grave", localization, graveOwner, "123452023072512345",
                true);
        // WHEN

        // GIVEN
        assertDoesNotThrow(() -> graveValidator.isValid(grave));
    }

    @Nested
    @DisplayName("type validation tests")
    class typeValidationTests {

        @Test
        @DisplayName("grave - double coffin grave")
        void whenInputDoubleCoffinGraveDoesNotThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", null, null,
                    null, null, null, null, null);
            var localization = new LocalizationDTO(1, "New moon cemetery", "A1", "7",
                    "18");
            var grave = new GraveDTO(2, "double coffin grave", localization, graveOwner,
                    "123452023072512345", true);
            // WHEN

            // GIVEN
            assertDoesNotThrow(() -> graveValidator.isValid(grave));
        }

        @Test
        @DisplayName("grave - urn grave")
        void whenInputUrnGraveDoesNotThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", null, null,
                    null, null, null, null, null);
            var localization = new LocalizationDTO(1, "New moon cemetery", "A1", "7",
                    "18");
            var grave = new GraveDTO(2, "urn grave", localization, graveOwner, "123452023072512345",
                    true);
            // WHEN

            // GIVEN
            assertDoesNotThrow(() -> graveValidator.isValid(grave));
        }

        @Test
        @DisplayName("grave - urn grave")
        void whenInputColumbariumDoesNotThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", null, null,
                    null, null, null, null, null);
            var localization = new LocalizationDTO(1, "New moon cemetery", "A1", "7",
                    "18");
            var grave = new GraveDTO(2, "columbarium", localization, graveOwner, "123452023072512345",
                    true);
            // WHEN

            // GIVEN
            assertDoesNotThrow(() -> graveValidator.isValid(grave));
        }

        @Test
        @DisplayName("grave - invalid grave type")
        void whenInputInvalidTypeThrowsException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", null, null,
                    null, null, null, null, null);
            var localization = new LocalizationDTO(1, "New moon cemetery", "A1", "7",
                    "18");
            var grave = new GraveDTO(2, "double urn grave", localization, graveOwner,
                    "123452023072512345", true);
            // WHEN

            // GIVEN
            assertThrows(InvalidInputException.class, () -> graveValidator.isValid(grave));
        }
    }
}
