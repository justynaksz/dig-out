package com.digout.webapp.service.exeption;

public class InvalidInputException extends Exception {

    public InvalidInputException(String fieldName, String message) {
        super("Incorrect value in " + fieldName + " . " + message);
    }
}
