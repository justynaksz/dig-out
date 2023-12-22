package com.digout.webapp.service.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class DeceasedDTO {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final LocalDate deathDate;
    private final boolean isInfectiousDisease;
    private final GraveDTO grave;

    public DeceasedDTO(int id, String firstName, String lastName, LocalDate birthDate, LocalDate deathDate,
                       boolean isInfectiousDisease, GraveDTO grave) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.isInfectiousDisease = isInfectiousDisease;
        this.grave = grave;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public boolean isInfectiousDisease() {
        return isInfectiousDisease;
    }

    public GraveDTO getGrave() {
        return grave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeceasedDTO that = (DeceasedDTO) o;
        return id == that.id && isInfectiousDisease == that.isInfectiousDisease
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(deathDate, that.deathDate)
                && Objects.equals(grave, that.grave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, deathDate, isInfectiousDisease, grave);
    }
}
