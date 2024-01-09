package com.digout.webapp.service.DTO;

import java.util.Objects;

public class GraveDTO implements DTO {

    private final int id;
    private final String type;
    private final LocalizationDTO localization;
    private final GraveOwnerDTO graveOwner;
    private final String photo;
    private final boolean isPlaceAvailable;



    public GraveDTO(int id, String type, LocalizationDTO localization, GraveOwnerDTO graveOwner,
                    String photo, boolean isPlaceAvailable) {
        this.id = id;
        this.type = type;
        this.localization = localization;
        this.graveOwner = graveOwner;
        this.photo = photo;
        this.isPlaceAvailable = isPlaceAvailable;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public LocalizationDTO getLocalization() {
        return localization;
    }

    public GraveOwnerDTO getGraveOwner() {
        return graveOwner;
    }

    public String getPhoto() {
        return photo;
    }

    public boolean isPlaceAvailable() {
        return isPlaceAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraveDTO graveDTO = (GraveDTO) o;
        return id == graveDTO.id && isPlaceAvailable == graveDTO.isPlaceAvailable
                && Objects.equals(type, graveDTO.type) && Objects.equals(localization, graveDTO.localization)
                && Objects.equals(graveOwner, graveDTO.graveOwner) && Objects.equals(photo, graveDTO.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, localization, graveOwner, photo, isPlaceAvailable);
    }
}
