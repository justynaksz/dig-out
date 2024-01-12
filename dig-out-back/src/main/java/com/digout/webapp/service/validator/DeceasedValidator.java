package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.DTO.DeceasedDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.DeathBeforeBirthDateException;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DeceasedValidator extends Validator {

    private final GraveValidator graveValidator = new GraveValidator();

    @Override
    public void isValid(DTO dto) throws InvalidInputException, EmptyFieldException, DeathBeforeBirthDateException {
        var deceased = (DeceasedDTO) dto;
        validateDeceased(deceased);
    }

    private void validateDeceased(DeceasedDTO deceased) throws InvalidInputException, EmptyFieldException, DeathBeforeBirthDateException {
        validateFirstName(deceased.getFirstName());
        validateLastName(deceased.getLastName());
        validateDates(deceased.getBirthDate(), deceased.getDeathDate());
        graveValidator.isValid(deceased.getGrave());
        validatePhoto(deceased.getPhoto());
    }

    private void validateDates(LocalDate birthDate, LocalDate deathDate) throws DeathBeforeBirthDateException {
        if(deathDate.isBefore(birthDate)) {
            throw new DeathBeforeBirthDateException(birthDate, deathDate);
        }
    }
}
