package com.digout.webapp.service.exeption;

public class NotFoundException extends Exception {
    public NotFoundException (String object, int id) {
        super(object + " of id " + id + " could not be found.");
    }

    public NotFoundException (String object, String nickname) {
        super(object + " of nickname: " + nickname + " could not be found.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
