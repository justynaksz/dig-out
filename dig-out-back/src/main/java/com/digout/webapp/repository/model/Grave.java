package com.digout.webapp.repository.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "grave")
public class Grave extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localization", referencedColumnName = "id")
    private Localization localization;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "grave_owner", referencedColumnName = "id")
    private GraveOwner graveOwner;

    @Column
    private String photo;

    @Column
    private boolean isPlaceAvailable;

    public Grave() {
    }

    public Grave(int id, String type, Localization localization, GraveOwner graveOwner,
                 String photo, boolean isPlaceAvailable) {
        this.id = id;
        this.type = type;
        this.localization = localization;
        this.graveOwner = graveOwner;
        this.photo = photo;
        this.isPlaceAvailable = isPlaceAvailable;
        tableName = "GRAVE";
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

    public GraveOwner getGraveOwner() {
        return graveOwner;
    }

    public void setGraveOwner(GraveOwner graveOwner) {
        this.graveOwner = graveOwner;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isPlaceAvailable() {
        return isPlaceAvailable;
    }

    public void setPlaceAvailable(boolean placeAvailable) {
        isPlaceAvailable = placeAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grave grave = (Grave) o;
        return id == grave.id && isPlaceAvailable == grave.isPlaceAvailable
                && Objects.equals(type, grave.type) && Objects.equals(localization, grave.localization)
                && Objects.equals(graveOwner, grave.graveOwner) && Objects.equals(photo, grave.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, localization, graveOwner, photo, isPlaceAvailable);
    }

    @Override
    public String toString() {
        return "Grave{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", localization=" + localization +
                ", graveOwner=" + graveOwner +
                ", photo='" + photo + '\'' +
                ", isPlaceAvailable=" + isPlaceAvailable +
                '}';
    }
}
