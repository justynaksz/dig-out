package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.DTO.DeceasedDTO;
import org.springframework.stereotype.Component;

@Component
public class DeceasedValidator extends Validator {

    @Override
    public void isValid(DTO dto) {
        var deceased = (DeceasedDTO) dto;
        validateDeceased(deceased);
    }

    private void validateDeceased(DeceasedDTO deceased) {
        validateFirstname(deceased.getFirstName());
        validateLastname(deceased.getLastName());
    }

    private void validateFirstname(String firstname) {

    }

    private void validateLastname(String lastname) {

    }


}
