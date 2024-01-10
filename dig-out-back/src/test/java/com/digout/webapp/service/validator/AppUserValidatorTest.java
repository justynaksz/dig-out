package com.digout.webapp.service.validator;

import com.digout.webapp.repository.role.Role;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
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
                Role.USER, null, null);
        // WHEN

        // THEN
        assertDoesNotThrow(() -> appUserValidator.isValid(appUser));
    }

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
        assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
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
        assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
    }

    @Test
    @DisplayName("app user - no special signs in password")
    void whenNoSpecialSignsIndUserPasswordThrowsException() {
        // GIVEN
        var appUser = new AppUserDTO(5, "digger", "justBeNice11", "digger2023@yahoo.com",
                Role.USER, null, null);
        // WHEN

        // THEN
        assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
    }

    @Test
    @DisplayName("app user - no numbers in password")
    void whenNoNumbersIndUserPasswordThrowsException() {
        // GIVEN
        var appUser = new AppUserDTO(5, "digger", "justBeNice!", "digger2023@yahoo.com",
                Role.USER, null, null);
        // WHEN

        // THEN
        assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
    }

    @Test
    @DisplayName("app user - no letters in password")
    void whenNoLettersIndUserPasswordThrowsException() {
        // GIVEN
        var appUser = new AppUserDTO(5, "digger", "63474311!", "digger2023@yahoo.com",
                Role.USER, null, null);
        // WHEN

        // THEN
        assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
    }

    @Test
    @DisplayName("app user - too short password")
    void whenTooShortUserPasswordThrowsException() {
        // GIVEN
        var appUser = new AppUserDTO(5, "digger", "Nice16!", "digger2023@yahoo.com",
                Role.USER, null, null);
        // WHEN

        // THEN
        assertThrows(InvalidInputException.class, () -> appUserValidator.isValid(appUser));
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
