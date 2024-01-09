package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class LocalizationValidator extends Validator{

    @Override
    public void isValid(DTO dto) throws InvalidInputException {
        var localization = (LocalizationDTO) dto;
        validateLocalization(localization);
    }

    private void validateLocalization(LocalizationDTO localization) throws InvalidInputException {
        validateCemetery(localization.getCemetery());
        validateQuarter(localization.getQuarter());
        validateColumnOrRow(localization.getLocalizationColumn());
        validateColumnOrRow(localization.getLocalizationRow());
    }

    private void validateQuarter(String quarter) throws InvalidInputException {

    }

    private void validateColumnOrRow(String value) {

    }

}
