package com.digout.webapp.service.exeption;

public class EmptyFieldException extends Exception {
    public EmptyFieldException(String field) {
        super("Field " + field + " cannot remain empty.");
    }
}
