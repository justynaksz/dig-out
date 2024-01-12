package com.digout.webapp.service.exeption;

import java.time.LocalDate;

public class DeathBeforeBirthDateException extends Exception {

    public DeathBeforeBirthDateException(LocalDate birthDate, LocalDate deathDate) {
        super("Death date (" + deathDate + ") cannot be before birth date (" + birthDate + ").");
    }
}
