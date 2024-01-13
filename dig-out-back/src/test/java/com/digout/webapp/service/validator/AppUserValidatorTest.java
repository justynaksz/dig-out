package com.digout.webapp.service.validator;

import com.digout.webapp.repository.role.Role;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.PatternBreakException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AppUserValidatorTest {

    private final AppUserValidator appUserValidator = new AppUserValidator();

    @Test
    @DisplayName("app user - valid")
    void whenInputValidUserDoesNotThrowException() {
        // GIVEN
        var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023@yahoo.com",
                Role.USER, null, "7859375205327");
        // WHEN

        // THEN
        assertDoesNotThrow(() -> appUserValidator.isValid(appUser));
    }

    @Nested
    @DisplayName("nickname validation tests")
    class nicknameValidationTests {

        @Test
        @DisplayName("app user - no nickname")
        void whenNoUserNicknameThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, null, "justBeNice11!", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - too short nickname")
        void whenTooShortUserNicknameThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "dig", "justBeNice11!", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - too long nickname")
        void whenTooLongUserNicknameThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger7694217^$^&*@(*^$*&(@73745632643227593259325732",
                    "justBeNice11!", "digger2023@yahoo.com", Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
        }
    }

    @Nested
    @DisplayName("password validation tests")
    class passwordValidationTests {

        @Test
        @DisplayName("app user - no password")
        void whenNoUserPasswordThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no upper case in password")
        void whenNoUpperCaseInUserPasswordThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justbenice11!", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no special signs in password")
        void whenNoSpecialSignsIndUserPasswordThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no numbers in password")
        void whenNoNumbersIndUserPasswordThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice!", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no letters in password")
        void whenNoLettersIndUserPasswordThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "63474311!", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - too short password")
        void whenTooShortUserPasswordThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "Nice16!", "digger2023@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - too long password")
        void whenTooLongUserPasswordThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger",
                    "digger7694217^$^&*$%^#^%*(())(&%$$&*&*@(*^$*&(@73745632643227593259325732",
                    "digger2023@yahoo.com", Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
        }
    }

    @Nested
    @DisplayName("e-mail validation tests")
    class emailValidationTests {
        @Test
        @DisplayName("app user - no e-mail")
        void whenNoUserEmailThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", null,
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no prefix in e-mail")
        void whenNoPrefixInUserEmailThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "@yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no @ in e-mail")
        void whenNoSpecialSignIndUserEmailThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023yahoo.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no middle in e-mail")
        void whenNoMiddleIndUserEmailThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023@.com",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no suffix in e-mail")
        void whenNoSuffixIndUserEmailThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023@yahoo",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - too long suffix in e-mail")
        void whenTooShortUserEmailThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023@yahoo.yahoohoocom",
                    Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - too long e-mail")
        void whenTooLongUserEmailThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!",
                    "digger2023aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2023@yahoo.com", Role.USER, null, null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
        }
    }

    @Nested
    @DisplayName("role validation tests")
    class roleValidationTests {

        @Test
        @DisplayName("app user - no role")
        void whenNoUserRoleThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!",
                    "digger2023@yahoo.com", "", null, null);
            // WHEN

            // THEN
            assertThrows(EmptyFieldException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - no admin or user role")
        void whenNoAdminOrUserInUserRoleThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023@yahoo.com",
                    "PERSON", null, null);
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
        }
    }

    @Nested
    @DisplayName("photo validation tests")
    class photoValidationTests {

        @Test
        @DisplayName("app user - letters in photo")
        void whenLettersInUserPhotoThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!",
                    "digger2023@yahoo.com", Role.USER, null, "some sweet selfie");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - special signs in photo")
        void whenSpecialSignsInUserPhotoThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023@yahoo.com",
                    Role.USER, null, "87932!5072983:");
            // WHEN

            // THEN
            assertThrows(PatternBreakException.class, () -> appUserValidator.isValid(appUser));
        }

        @Test
        @DisplayName("app user - too long photo")
        void whenTooLongUserPhotoThrowsException() {
            // GIVEN
            var appUser = new AppUserDTO(5, "digger", "justBeNice11!", "digger2023@yahoo.com",
                    Role.USER, null,
                    "879356326465463643626463464364364364364364363463464636436431676876967907342346347547653864542754754753737547575375435072983879356326465463643626463464364364364364364363463464636436431676876967907342346347547653864542754754753737547575375435072983");
            // WHEN

            // THEN
            assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
        }
    }
}
