package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.DTO.GraveDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class GraveValidator extends Validator {

    private final LocalizationValidator localizationValidator = new LocalizationValidator();
    private final GraveOwnerValidator graveOwnerValidator = new GraveOwnerValidator();

    @Override
    public void isValid(DTO dto) throws InvalidInputException, EmptyFieldException {
        var grave = (GraveDTO) dto;
        validateGrave(grave);
    }

    public void validateAvailabilityInNewGraves (boolean isPlaceAvailable) throws InvalidInputException {
        if (!isPlaceAvailable) {
            throw new InvalidInputException("IS_PLACE_AVAILABLE", String.valueOf(isPlaceAvailable),
                    "New grave cannot be created as unavailable.");
        }
    }

    private void validateGrave(GraveDTO grave) throws InvalidInputException, EmptyFieldException {
        validateType(grave.getType());
        localizationValidator.isValid(grave.getLocalization());
        graveOwnerValidator.isValid(grave.getGraveOwner());
        validatePhoto(grave.getPhoto());
    }

    private void validateType(String type) throws EmptyFieldException, InvalidInputException {
        var typeField = "TYPE";
        validateNotEmptyOrNull(type, typeField);
        var coffin = "coffin grave";
        var doubleCoffin = "double coffin grave";
        var urn = "urn grave";
        var columbarium = "columbarium";
        if(!type.equals(coffin)
                && !type.equals(doubleCoffin)
                && !type.equals(urn)
                && !type.equals(columbarium)) {
            throw new InvalidInputException(typeField, type, "Available grave types: " + coffin + ", "
                    + doubleCoffin + ", " + urn + ", " + columbarium);
        }
    }
}
