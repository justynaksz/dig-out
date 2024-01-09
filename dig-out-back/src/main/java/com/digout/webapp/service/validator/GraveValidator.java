package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class GraveValidator extends Validator {


    @Override
    public void isValid(DTO dto) throws InvalidInputException {

    }

    public void validateCemetery(String cemetery) throws InvalidInputException {
        super.validateCemetery(cemetery);
    }
}
