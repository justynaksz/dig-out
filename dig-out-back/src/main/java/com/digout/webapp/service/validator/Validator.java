package com.digout.webapp.service.validator;

import com.digout.webapp.service.exeption.InvalidInputException;

public abstract class Validator {

    public void validateId(int id) throws InvalidInputException {
        if (id <= 0) {
            throw new InvalidInputException("ID", "Id value must be positive.");
        }
    }

    public void validateLength(String fieldName, int maxLength, int inputLength) throws InvalidInputException {
        if (inputLength > maxLength) {
            throw new InvalidInputException(fieldName, "Input too long.");
        }
    }

    public void validateCemeteryLength(String cemetery) throws InvalidInputException {
        validateLength("CEMETERY", 150, cemetery.length());
    }





}
