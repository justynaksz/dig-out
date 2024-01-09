package com.digout.webapp.service.DTO;

import java.util.Objects;

public class LocalizationDTO implements DTO {


    private final int id;
    private final String cemetery;
    private final String quarter;
    private final String localizationRow;
    private final String localizationColumn;

    public LocalizationDTO(int id, String cemetery, String quarter,
                           String localizationRow, String localizationColumn) {
        this.id = id;
        this.cemetery = cemetery;
        this.quarter = quarter;
        this.localizationRow = localizationRow;
        this.localizationColumn = localizationColumn;
    }

    public int getId() {
        return id;
    }

    public String getCemetery() {
        return cemetery;
    }

    public String getQuarter() {
        return quarter;
    }

    public String getLocalizationRow() {
        return localizationRow;
    }

    public String getLocalizationColumn() {
        return localizationColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizationDTO that = (LocalizationDTO) o;
        return id == that.id && Objects.equals(cemetery, that.cemetery)
                && Objects.equals(quarter, that.quarter)
                && Objects.equals(localizationRow, that.localizationRow)
                && Objects.equals(localizationColumn, that.localizationColumn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cemetery, quarter, localizationRow, localizationColumn);
    }
}
