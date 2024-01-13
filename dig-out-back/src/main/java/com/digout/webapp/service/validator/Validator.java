package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.exeption.DeathBeforeBirthDateException;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.PatternBreakException;

public abstract class Validator {

    public abstract void isValid(DTO dto) throws InvalidInputException, EmptyFieldException, DeathBeforeBirthDateException;

    public void validateId(int id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("ID", String.valueOf(id), "Id value must be positive.");
        }
    }

    protected void validateLength(String fieldName, int maxSize, String value) throws InvalidInputException {
        if (value != null && value.getBytes().length > maxSize) {
            throw new InvalidInputException(fieldName, value, "Too long value provided.");
        }
    }

    protected void validateNotEmptyOrNull(String value, String field) throws EmptyFieldException {
        if (value == null || value.trim().isEmpty()) {
            throw new EmptyFieldException(field);
        }
    }

    protected void validatePattern(String value, String field, String regex) throws PatternBreakException {
        if (value != null && !value.matches(regex)) {
            throw new PatternBreakException(field, value);
        }
    }

    public void validateCemetery(String cemetery) throws InvalidInputException, EmptyFieldException {
        String cemeteryField = "CEMETERY";
        validateNotEmptyOrNull(cemetery, cemeteryField);
        validateLength(cemeteryField, 200, cemetery);
        var regex = "^[a-zA-Z1-9àáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð '-]{3,}$";
        validatePattern(cemetery, cemeteryField, regex);
    }

    protected void validatePhoto(String photo) throws InvalidInputException {
        String photoField = "PHOTO";
        validateLength(photoField, 200, photo);
        validatePattern(photo, photoField, "^[0-9]+$");
    }

    protected void validateFirstName(String name) throws EmptyFieldException, InvalidInputException {
        validateFirstAndLastName(name, "FIRST_NAME");
    }

    protected void validateLastName(String name) throws EmptyFieldException, InvalidInputException {
        validateFirstAndLastName(name, "LAST_NAME");
    }

    private void validateFirstAndLastName(String name, String field) throws EmptyFieldException, InvalidInputException {
        validateNotEmptyOrNull(name, field);
        validateLength(field, 50, name);
        var regex = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
        validatePattern(name, field, regex);
    }
}
