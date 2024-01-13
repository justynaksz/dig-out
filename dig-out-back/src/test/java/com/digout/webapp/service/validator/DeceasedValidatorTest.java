package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DeceasedDTO;
import com.digout.webapp.service.DTO.GraveDTO;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.exeption.DeathBeforeBirthDateException;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeceasedValidatorTest {

    private final DeceasedValidator deceasedValidator = new DeceasedValidator();

    @Test
    @DisplayName("deceased - valid")
    void whenValidInputDeceasedDoesNotThrowException() {
        // GIVEN
        var localization = new LocalizationDTO(3, "New moon cemetery", "B4", "4",
                "A");
        var graveOwner = new GraveOwnerDTO(1, "Emily", "Blunt", "88121417864",
                "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain",
                "+48 459-782-145");
        var grave = new GraveDTO(3, "urn grave", localization, graveOwner, "123452020011812345",
                true);
        var deceased = new DeceasedDTO(7, "Collin", "Moody",
                LocalDate.of(1964, 4, 19), LocalDate.of(2023, 1, 7),
                false, grave, "1234564565412345");
        // WHEN

        // THEN
        assertDoesNotThrow(() -> deceasedValidator.isValid(deceased));
    }

    @Test
    @DisplayName("negative id")
    void whenNegativeIdThrowsException() {
        // GIVEN
        var id = -7;
        // WHEN

        // THEN
        assertThrows(InvalidInputException.class, () -> deceasedValidator.validateId(id));
    }

    @Test
    @DisplayName("deceased - death before birth date")
    void whenDeathBeforeBirthDateThrowsException() {
        // GIVEN
        var localization = new LocalizationDTO(3, "New moon cemetery", "B4", "4",
                "A");
        var graveOwner = new GraveOwnerDTO(1, "Emily", "Blunt", "88121417864",
                "5th Avenue", "18", "Brigthtown", "47-427", "Great Britain",
                "459-782-145");
        var grave = new GraveDTO(3, "urn grave", localization, graveOwner, "123452020011812345",
                true);
        var deceased = new DeceasedDTO(7, "Collin", "Moody",
                LocalDate.of(2023, 1, 7), LocalDate.of(1964, 4, 19),
                false, grave, "1234564565412345");
        // WHEN

        // THEN
        assertThrows(DeathBeforeBirthDateException.class, () -> deceasedValidator.isValid(deceased));
    }
}
