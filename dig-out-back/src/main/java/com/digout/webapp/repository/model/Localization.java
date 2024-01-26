package com.digout.webapp.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "localization")
public class Localization extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String cemetery;

    @Column
    private String quarter;

    @Column
    private String localizationRow;

    @Column
    private String localizationColumn;

    public Localization() {
    }

    public Localization(int id, String cemetery, String quarter, String localizationRow, String localizationColumn) {
        this.id = id;
        this.cemetery = cemetery;
        this.quarter = quarter;
        this.localizationRow = localizationRow;
        this.localizationColumn = localizationColumn;
        tableName = "LOCALIZATION";
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

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getLocalizationRow() {
        return localizationRow;
    }

    public void setLocalizationRow(String localizationRow) {
        this.localizationRow = localizationRow;
    }

    public String getLocalizationColumn() {
        return localizationColumn;
    }

    public void setLocalizationColumn(String localizationColumn) {
        this.localizationColumn = localizationColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localization that = (Localization) o;
        return id == that.id && Objects.equals(cemetery, that.cemetery) && Objects.equals(quarter, that.quarter) && Objects.equals(localizationRow, that.localizationRow) && Objects.equals(localizationColumn, that.localizationColumn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cemetery, quarter, localizationRow, localizationColumn);
    }

    @Override
    public String toString() {
        return "Localization{" +
                "id=" + id +
                ", cemetery='" + cemetery + '\'' +
                ", quarter='" + quarter + '\'' +
                ", localizationRow='" + localizationRow + '\'' +
                ", localizationColumn='" + localizationColumn + '\'' +
                '}';
    }
}
