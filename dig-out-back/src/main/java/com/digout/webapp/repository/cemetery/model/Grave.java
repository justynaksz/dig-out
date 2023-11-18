package com.digout.webapp.repository.cemetery.model;

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
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localization", referencedColumnName = "id")
    private Localization localization;

    @Column
    private String graveOwner;

    public Grave() {
    }

    public Grave(int id, String type, Localization localization, String graveOwner) {
        this.id = id;
        this.type = type;
        this.localization = localization;
        this.graveOwner = graveOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grave grave = (Grave) o;
        return id == grave.id && Objects.equals(type, grave.type) && Objects.equals(localization, grave.localization) && Objects.equals(graveOwner, grave.graveOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, localization, graveOwner);
    }

    @Override
    public String toString() {
        return "Grave{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", localization=" + localization +
                ", graveOwner='" + graveOwner +
                '}';
    }
}
