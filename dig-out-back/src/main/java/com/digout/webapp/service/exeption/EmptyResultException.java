package com.digout.webapp.service.exeption;

public class EmptyResultException extends Exception {
    public EmptyResultException(String type) {
        super("No records of " + type + " has been found by given criteria.");
    }
}
