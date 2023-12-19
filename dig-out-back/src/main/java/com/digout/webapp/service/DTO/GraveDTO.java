package com.digout.webapp.service.DTO;

import com.digout.webapp.repository.cemetery.model.GraveOwner;
import com.digout.webapp.repository.cemetery.model.Localization;
import java.util.Objects;

public class GraveDTO {

    private final int id;
    private final String type;
    private final Localization localization;
    private final GraveOwner graveOwner;
    private final String photo;

    public GraveDTO(int id, String type, Localization localization, 
                    GraveOwner graveOwner, String photo) {
        this.id = id;
        this.type = type;
        this.localization = localization;
        this.graveOwner = graveOwner;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Localization getLocalization() {
        return localization;
    }

    public GraveOwner getGraveOwner() {
        return graveOwner;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraveDTO graveDTO = (GraveDTO) o;
        return id == graveDTO.id && Objects.equals(type, graveDTO.type)
                && Objects.equals(localization, graveDTO.localization)
                && Objects.equals(graveOwner, graveDTO.graveOwner)
                && Objects.equals(photo, graveDTO.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, localization, graveOwner, photo);
    }
}
