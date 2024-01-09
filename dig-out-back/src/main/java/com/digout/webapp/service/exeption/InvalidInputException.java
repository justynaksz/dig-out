package com.digout.webapp.service.exeption;

public class InvalidInputException extends Exception {

    public InvalidInputException(String fieldName, String value, String message) {
        super("Incorrect value in " + fieldName + ": " + value + " . " + message);
    }
}
