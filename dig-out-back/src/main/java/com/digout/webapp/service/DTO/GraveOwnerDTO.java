package com.digout.webapp.service.DTO;

import java.util.Objects;

public class GraveOwnerDTO {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String pesel;
    private final String street;
    private final String parcel;
    private final String city;
    private final String postalCode;
    private final String country;
    private final String phoneNumber;

    public GraveOwnerDTO(int id, String firstName, String lastName, String pesel, String street, String parcel,
                         String city, String postalCode, String country, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.street = street;
        this.parcel = parcel;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
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

    public String getPesel() {
        return pesel;
    }

    public String getStreet() {
        return street;
    }

    public String getParcel() {
        return parcel;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraveOwnerDTO that = (GraveOwnerDTO) o;
        return id == that.id && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(pesel, that.pesel)
                && Objects.equals(street, that.street)
                && Objects.equals(parcel, that.parcel)
                && Objects.equals(city, that.city)
                && Objects.equals(postalCode, that.postalCode)
                && Objects.equals(country, that.country)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, pesel, street, parcel, city, postalCode, country, phoneNumber);
    }
}
