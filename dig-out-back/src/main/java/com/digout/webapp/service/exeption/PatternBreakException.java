package com.digout.webapp.service.exeption;

public class PatternBreakException extends InvalidInputException {

    public PatternBreakException(String fieldName, String value) {
        super(fieldName, value, "Input doesn't match the expected pattern.");
    }
}
