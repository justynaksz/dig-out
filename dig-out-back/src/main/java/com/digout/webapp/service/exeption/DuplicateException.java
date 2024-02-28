package com.digout.webapp.service.exeption;

public class DuplicateException extends Exception {

    public DuplicateException() {
        super("User with given email or nickname already exists.");
    }
}
