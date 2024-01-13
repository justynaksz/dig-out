package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.PatternBreakException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraveOwnerValidatorTest {

    private final GraveOwnerValidator graveOwnerValidator = new GraveOwnerValidator();

    @Test
    @DisplayName("grave owner - valid")
    void whenInputValidGraveOwnerDoesNotThrowException() {
        // GIVEN
        var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson-Robertson", null,
                "ul. Thomasa Wöödrowa Wilsona", null, "Knurów", null,
                "Burkina-Faso", null);
        // WHEN

        // THEN
        assertDoesNotThrow(() -> graveOwnerValidator.isValid(graveOwner));
    }

    @Nested
    @DisplayName("first & last name validation tests")
    class firstAndLastNameValidationTests {

        @Test
        @DisplayName("grave owner - no first name")
        void whenNoFirstNameThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, null, "Watson van Robertson", null,
                    null, null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - no last name")
        void whenNoLastNameThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", " ", null, null,
                    null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign in name")
        void whenNotAllowedSignInFirstOrLastNameThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "W@tson", null, null,
                    null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign in name")
        void whenNumberInFirstOrLastNameThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert, Jr. 3", "Watson", null,
                    null, null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - too long first or last name")
        void whenTooLongFirstOrLastNameThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert Sean Paul Riccardo Alan Stephan Anthony Gabriel",
                    "Watson", null, null, null, null, null, null,
                    null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }
    }

    @Nested
    @DisplayName("pesel validation tests")
    class peselValidationTests {

        @Test
        @DisplayName("grave owner - letters in pesel")
        void whenLetterInPeselThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "9912170274K",
                    null, null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign in pesel")
        void whenNotAllowedSignInPeselThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99!2!702747",
                    null, null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - too long pesel")
        void whenTooLongPeselThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "991217027479",
                    null, null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - too short pesel")
        void whenTooShortPeselThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "9912170274",
                    null, null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }
    }

    @Nested
    @DisplayName("street validation tests")
    class streetValidationTests {

        @Test
        @DisplayName("grave owner - too long street")
        void whenTooLongStreetThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa Wöödrowa Wilsona Thomasa Wöödrowa Wilsona Thomasa Wöödrowa Wilsona Thomasa " +
                            "Wöödrowa Wilsona", null, null, null, null, null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign in street")
        void whenNotAllowedSignInStreetThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa_Wöödrowa_Wilsona", null, null, null, null,
                    null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }
    }

    @Nested
    @DisplayName("parcel validation tests")
    class parcelValidationTests {

        @Test
        @DisplayName("grave owner - too long parcel")
        void whenTooLongParcelThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa Wöödrowa Wilsona", null, null, "177894A/157", null,
                    null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign in parcel")
        void whenNotAllowedSignInParcelThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa_Wöödrowa_Wilsona", null, null, "17A:1", null,
                    null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }
    }

    @Nested
    @DisplayName("city validation tests")
    class cityValidationTests {

        @Test
        @DisplayName("grave owner - too long city")
        void whenTooLongCityThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa Wöödrowa Wilsona", null,
                    "Great Big Huge Enormous Giant Large City In Some Country", null, null,
                    null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign in city")
        void whenNotAllowedSignInCityThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa_Woodrowa_Wilsona", null, "New York C!ty", null,
                    null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }
    }

    @Test
    @DisplayName("grave owner - too long postal code")
    void whenTooLongPostalCodeThrowException() {
        // GIVEN
        var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                "ul. Thomasa Wöödrowa Wilsona", null, null, "A0/1001-187", null,
                null);
        // WHEN

        // THEN
        assertThrows(InvalidInputException.class, () -> graveOwnerValidator.isValid(graveOwner));
    }

    @Nested
    @DisplayName("country validation tests")
    class countryValidationTests {

        @Test
        @DisplayName("grave owner - too long country")
        void whenTooLongCountryThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa Wöödrowa Wilsona", null, null, null,
                    "Great Big Huge Enormous Giant Large Country Somewhere", null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign in city")
        void whenNotAllowedSignInCityThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson", "99121702747",
                    "ul. Thomasa Wöödrowa Wilsona", null, null, null,
                    "Piękny kraj na północy", null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }
    }

    @Nested
    @DisplayName("phone number validation tests")
    class phoneNumberValidationTests {

        @Test
        @DisplayName("grave owner - valid phone number")
        void whenValidPhoneNumberDoesNotThrowException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson-Robertson", null,
                    null, null, null, null, null, "697654875");
            // WHEN

            // THEN
            assertDoesNotThrow(() -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - letter in phone number")
        void whenLettersInPhoneNumberThrowsException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson-Robertson", null,
                    null, null, null, null, null, "A697654875");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - not allowed sign phone number")
        void whenSpecialSignInPhoneNumberThrowsException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson-Robertson", null,
                    null, null, null, null, null, "+(69)7654875");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - valid phone number")
        void whenTooLongPhoneNumberThrowsException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson-Robertson", null,
                    null, null, null, null, null, "697-654-875-7482");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }

        @Test
        @DisplayName("grave owner - valid phone number")
        void whenTooShortPhoneNumberThrowsException() {
            // GIVEN
            var graveOwner = new GraveOwnerDTO(3, "Robert", "Watson-Robertson", null,
                    null, null, null, null, null, "69765");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> graveOwnerValidator.isValid(graveOwner));
        }
    }
}
