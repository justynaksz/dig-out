package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class GraveOwnerValidator extends Validator {
    @Override
    public void isValid(DTO dto) throws InvalidInputException, EmptyFieldException {
        var graveOwner = (GraveOwnerDTO) dto;
        validateFirstName(graveOwner.getFirstName());
        validateLastName(graveOwner.getLastName());
        validatePesel(graveOwner.getPesel());
        validateStreet(graveOwner.getStreet());
        validateParcel(graveOwner.getParcel());
        validateCity(graveOwner.getCity());
        validatePostalCode(graveOwner.getPostalCode());
        validateCountry(graveOwner.getCountry());
        validatePhoneNumber(graveOwner.getPhoneNumber());
    }

    private void validatePesel(String pesel) throws InvalidInputException {
        var peselField = "PESEL";
        validateLength(peselField, 50, pesel);
        validatePattern(pesel, peselField, "^[0-9]{11}$");
    }

    private void validateStreet(String street) throws InvalidInputException {
        var streetField = "STREET";
        validateLength(streetField, 100, street);
        var regex = "/^[a-zA-Z1-9àáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u";
        validatePattern(street, streetField, regex);
    }

    private void validateParcel(String parcel) throws InvalidInputException {
        var parcelField = "PARCEL";
        validateLength(parcelField, 10, parcel);
        var regex = "^[1-9]\\d*(?: ?(?:[a-z]|[/-] ?\\d+[a-z]?))?$";
        validatePattern(parcel, parcelField, regex);
    }

    private void validateCity(String city) throws InvalidInputException {
        var cityField = "CITY";
        validateLength(cityField, 50, city);
        var letters = "[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð]";
        var regex = "^" + letters +"+(?:(?:\\\\s+|-)" + letters + "+)*$";
        validatePattern(city, cityField, regex);
    }

    // Every postal code system uses only A-Z and/or 0-9 and sometimes space/dash
    //
    //The shortest postal code format is Sierra Leone with NN
    //
    //The longest is American Samoa with NNNNN-NNNNNN
    //
    //You should allow one space or dash.
    //
    //Should not begin or end with space or dash
    private void validatePostalCode(String postalCode) throws InvalidInputException {
        var postalCodeField = "POSTAL_CODE";
        validateLength(postalCodeField, 10, postalCode);
        var regex = "(?i)^[a-z0-9][a-z0-9\\- ]{0,10}[a-z0-9]$";
        validatePattern(postalCode, postalCodeField, regex);
    }

    //All names must start with a capital letter, so something like "United States" is valid but "United states" is not. The weakness, of course, is that "United States of America" isn't considered valid.
    //
    //Also, the first letter can be the only capital letter in the word. This is to ban stuff like "UNited States." Of course, it also bans stuff like "USA".
    // and 'of the' 'and the' of
    //One-letter country names are specifically not allowed (i.e. all country names must have at least two letters).
    private void validateCountry(String country) throws InvalidInputException {
        var countryField = "COUNTRY";
        validateLength(countryField, 50, country);
        var regex = "^[A-Z][a-z]+( -[A-Z][a-z]+)*$";
        validatePattern(country, countryField, regex);
    }

    private void validatePhoneNumber(String phoneNumber) throws InvalidInputException {
        var phoneNumberField = "PHONE_NUMBER";
        validateLength(phoneNumberField, 50, phoneNumber);
        validatePattern(phoneNumber, phoneNumberField, "[0-9]{6,12}");
    }
}
