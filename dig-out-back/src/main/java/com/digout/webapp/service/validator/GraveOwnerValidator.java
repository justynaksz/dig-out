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
        validatePattern(pesel, peselField, "^[0-9]{11}$");
    }

    private void validateStreet(String street) throws InvalidInputException {
        var streetField = "STREET";
        validateLength(streetField, 100, street);
        var regex = "^[a-zA-Z1-9àáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";
        validatePattern(street, streetField, regex);
    }

    private void validateParcel(String parcel) throws InvalidInputException {
        var parcelField = "PARCEL";
        validateLength(parcelField, 10, parcel);
        var regex = "^[a-zA-Z1-9 /-]+$";
        validatePattern(parcel, parcelField, regex);
    }

    private void validateCity(String city) throws InvalidInputException {
        var cityField = "CITY";
        validateLength(cityField, 50, city);
        var letters = "[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð]";
        var regex = "^" + letters +"+(?:(?:\\\\s+|-)" + letters + "+)*$";
        validatePattern(city, cityField, regex);
    }

    private void validatePostalCode(String postalCode) throws InvalidInputException {
        var postalCodeField = "POSTAL_CODE";
        validateLength(postalCodeField, 10, postalCode);
    }

    private void validateCountry(String country) throws InvalidInputException {
        var countryField = "COUNTRY";
        validateLength(countryField, 50, country);
        var regex = "^[A-Z][a-z]+([ -][A-Z][a-z]+)*$";
        validatePattern(country, countryField, regex);
    }

    private void validatePhoneNumber(String phoneNumber) throws InvalidInputException {
        var phoneNumberField = "PHONE_NUMBER";
        validatePattern(phoneNumber, phoneNumberField, "[+]?[0-9- ]{6,15}");
    }
}
