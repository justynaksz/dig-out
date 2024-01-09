package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.PatternBreakException;

public abstract class Validator {

    public abstract void isValid(DTO dto) throws InvalidInputException;

    public void validateId(int id) throws InvalidInputException {
        if (id <= 0) {
            throw new InvalidInputException("ID", String.valueOf(id), "Id value must be positive.");
        }
    }

    protected void validateLength(String fieldName, int maxSize, String value) throws InvalidInputException {
        if (value.getBytes().length > maxSize) {
            throw new InvalidInputException(fieldName, value, "Too long value provided.");
        }
    }


    // TODO fix the pattern (just copied for now)
    protected void validateCemetery(String cemetery) throws InvalidInputException {
        String cemeteryField = "CEMETERY";
        validateLength(cemeteryField, 200, cemetery);
        if (!cemetery.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
            throw new PatternBreakException(cemeteryField, cemetery);
        }
    }

    protected void validatePhoto(String photo) throws InvalidInputException {
        String photoField = "PHOTO";
        validateLength(photoField, 200, photo);
    }
}
