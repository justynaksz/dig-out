package com.digout.webapp.repository.cemetery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "deceased")
public class Deceased {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deathDate;

    @Column
    private boolean isInfectiousDisease;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "grave", referencedColumnName = "id", nullable = false)
    private Grave grave;

    @Column
    private String photo;


    public Deceased() {
    }

    public Deceased(int id, String firstName, String lastName, LocalDate birthDate,
                    LocalDate deathDate, boolean isInfectiousDisease, Grave grave, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.isInfectiousDisease = isInfectiousDisease;
        this.grave = grave;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public boolean isInfectiousDisease() {
        return isInfectiousDisease;
    }

    public void setInfectiousDisease(boolean infectiousDisease) {
        isInfectiousDisease = infectiousDisease;
    }

    public Grave getGrave() {
        return grave;
    }

    public void setGrave(Grave grave) {
        this.grave = grave;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deceased deceased = (Deceased) o;
        return id == deceased.id && isInfectiousDisease == deceased.isInfectiousDisease
                && Objects.equals(firstName, deceased.firstName) && Objects.equals(lastName, deceased.lastName)
                && Objects.equals(birthDate, deceased.birthDate) && Objects.equals(deathDate, deceased.deathDate)
                && Objects.equals(grave, deceased.grave) && Objects.equals(photo, deceased.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, deathDate, isInfectiousDisease, grave, photo);
    }

    @Override
    public String toString() {
        return "Deceased{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                ", isInfectiousDisease=" + isInfectiousDisease +
                ", grave=" + grave +
                ", photo='" + photo + '\'' +
                '}';
    }
}
