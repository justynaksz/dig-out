package com.digout.repository.cemetery.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "grave")
public class Grave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String cemetery;

    @Column
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localization", referencedColumnName = "id")
    private Localization localization;

    @Column
    private String graveOwner;

    @Column
    private boolean availability;

    public Grave() {
    }

    public Grave(int id, String cemetery, String type, Localization localization, String graveOwner, boolean availability) {
        this.id = id;
        this.cemetery = cemetery;
        this.type = type;
        this.localization = localization;
        this.graveOwner = graveOwner;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCemetery() {
        return cemetery;
    }

    public void setCemetery(String cemetery) {
        this.cemetery = cemetery;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public String getGraveOwner() {
        return graveOwner;
    }

    public void setGraveOwner(String graveOwner) {
        this.graveOwner = graveOwner;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grave grave = (Grave) o;
        return id == grave.id && availability == grave.availability && Objects.equals(cemetery, grave.cemetery) && Objects.equals(type, grave.type) && Objects.equals(localization, grave.localization) && Objects.equals(graveOwner, grave.graveOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cemetery, type, localization, graveOwner, availability);
    }

    @Override
    public String toString() {
        return "Grave{" +
                "id=" + id +
                ", cemetery='" + cemetery + '\'' +
                ", type='" + type + '\'' +
                ", localization=" + localization +
                ", graveOwner='" + graveOwner + '\'' +
                ", availability=" + availability +
                '}';
    }
}
