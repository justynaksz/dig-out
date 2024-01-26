package com.digout.webapp.service.validator;

import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.DTO.LocalizationDTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.PatternBreakException;
import org.springframework.stereotype.Component;

@Component
public class LocalizationValidator extends Validator {

    @Override
    public void isValid(DTO dto) throws InvalidInputException, EmptyFieldException {
        var localization = (LocalizationDTO) dto;
        validateLocalization(localization);
    }

    private void validateLocalization(LocalizationDTO localization) throws InvalidInputException, EmptyFieldException {
        validateCemetery(localization.getCemetery());
        validateQuarter(localization.getQuarter());
        validateColumn(localization.getLocalizationColumn());
        validateRow(localization.getLocalizationRow());
    }

    private void validateQuarter(String quarter) throws InvalidInputException, EmptyFieldException {
        validateQuarterColumnOrRow(quarter, "QUARTER");
    }

    private void validateColumn(String column) throws InvalidInputException, EmptyFieldException {
        validateQuarterColumnOrRow(column, "COLUMN");
    }

    private void validateRow(String row) throws InvalidInputException, EmptyFieldException {
        validateQuarterColumnOrRow(row, "ROW");
    }

    private void validateQuarterColumnOrRow(String value, String field)
            throws EmptyFieldException, InvalidInputException {
        validateNotEmptyOrNull(value, field);
        validateLength(field, 8, value);
        validatePattern(value, field);
    }

    private void validatePattern(String value, String field) throws PatternBreakException {
        validatePattern(value, field, "^[a-zA-Z0-9]+[a-zA-Z0-9 /-]?[a-zA-Z0-9]*$");
    }
}
