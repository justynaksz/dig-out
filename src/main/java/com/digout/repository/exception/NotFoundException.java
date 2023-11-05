package com.digout.repository.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Element not found.");
    }
}
