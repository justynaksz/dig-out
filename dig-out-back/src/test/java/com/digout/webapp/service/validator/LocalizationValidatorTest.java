package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.PatternBreakException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalizationValidatorTest {

    private final LocalizationValidator localizationValidator = new LocalizationValidator();

    @Test
    @DisplayName("localization - valid")
    void whenValidInputLocalizationDoesNotThrowException() {
        // GIVEN
        var localization = new LocalizationDTO(1, "Księżycowy cmentarz", "A1",
                "7", "18");
        // WHEN

        // THEN
        assertDoesNotThrow(() -> localizationValidator.isValid(localization));
    }

    @Nested
    @DisplayName("cemetery validation tests")
    class cemeteryValidationTests {

        @Test
        @DisplayName("localization - no cemetery")
        void whenNoCemeteryThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, null, "A1",
                    "7", "18");
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> localizationValidator.isValid(localization));
        }

        @Test
        @DisplayName("localization - not allowed sign in cemetery")
        void whenNotAllowedSignInCemeteryThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "New_moon_cemetery", "A1",
                    "7", "18");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> localizationValidator.isValid(localization));
        }

        @Test
        @DisplayName("localization - too short cemetery")
        void whenTooShortCemeteryThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "CM", "A1",
                    "7", "18");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> localizationValidator.isValid(localization));
        }

        @Test
        @DisplayName("localization - too long cemetery")
        void whenTooLongCemeteryThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "New moon cemetery New moon cemetery" +
                    " New moon cemetery New moon cemetery New moon cemetery New moon cemetery New moon cemetery" +
                    "New moon cemetery New moon cemetery New moon cemetery New moon cemetery New moon cemetery" +
                    "New moon cemetery New moon cemetery New moon cemetery New moon cemetery",
                    "A1", "7", "18");
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> localizationValidator.isValid(localization));
        }
    }

    @Nested
    @DisplayName("quarter, column and row validation tests")
    class quarterColumnAndRowValidationTests {

        @Test
        @DisplayName("localization - empty")
        void whenEmptyThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "Księżycowy cmentarz", "A/1",
                    "7", "");
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> localizationValidator.isValid(localization));
        }

        @Test
        @DisplayName("localization - too many special signs")
        void whenTooManySpecialSignsThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "Księżycowy cmentarz", "A//1",
                    "7", "18");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> localizationValidator.isValid(localization));
        }

        @Test
        @DisplayName("localization - begins with special sign")
        void whenBeginsWithSpecialSignThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "Księżycowy cmentarz", "A/1",
                    "-7", "18");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> localizationValidator.isValid(localization));
        }

        @Test
        @DisplayName("localization - not allowed special sign")
        void whenNotAllowedSpecialSignThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "Księżycowy cmentarz", "A/1",
                    "7", "1_8");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> localizationValidator.isValid(localization));
        }

        @Test
        @DisplayName("localization - too long")
        void whenTooLongThrowsException() {
            // GIVEN
            var localization = new LocalizationDTO(1, "Księżycowy cmentarz", "A/1",
                    "ABC0/7742", "18");
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> localizationValidator.isValid(localization));
        }
    }
}
