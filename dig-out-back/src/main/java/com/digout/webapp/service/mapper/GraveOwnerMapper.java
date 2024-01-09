package com.digout.webapp.service.mapper;

import com.digout.webapp.repository.model.GraveOwner;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import org.springframework.stereotype.Component;

@Component
public class GraveOwnerMapper {

    public GraveOwner toModel(GraveOwnerDTO graveOwnerDTO) {
        var id = graveOwnerDTO.getId();
        var firstName = graveOwnerDTO.getFirstName();
        var lastName = graveOwnerDTO.getLastName();
        var pesel = graveOwnerDTO.getPesel();
        var street = graveOwnerDTO.getStreet();
        var parcel = graveOwnerDTO.getParcel();
        var city = graveOwnerDTO.getCity();
        var postalCode = graveOwnerDTO.getPostalCode();
        var country = graveOwnerDTO.getCountry();
        var phoneNumber = graveOwnerDTO.getPhoneNumber();
        return new GraveOwner(id, firstName, lastName, pesel, street, parcel, city, postalCode,
                country, phoneNumber);
    }

    public GraveOwnerDTO toDTO(GraveOwner graveOwner) {
        var id = graveOwner.getId();
        var firstName = graveOwner.getFirstName();
        var lastName = graveOwner.getLastName();
        var pesel = graveOwner.getPesel();
        var street = graveOwner.getStreet();
        var parcel = graveOwner.getParcel();
        var city = graveOwner.getCity();
        var postalCode = graveOwner.getPostalCode();
        var country = graveOwner.getCountry();
        var phoneNumber = graveOwner.getPhoneNumber();
        return new GraveOwnerDTO(id, firstName, lastName, pesel, street, parcel, city, postalCode,
                country, phoneNumber);
    }
}
