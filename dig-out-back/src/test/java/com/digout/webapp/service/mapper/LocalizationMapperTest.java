package com.digout.webapp.service.mapper;

import com.digout.webapp.repository.model.Localization;
import com.digout.webapp.service.DTO.LocalizationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationMapperTest {

    private final LocalizationMapper localizationMapper = new LocalizationMapper();
    private final int id = 1;
    private final String cemetery = "New moon cemetery";
    private final String quarter = "A18";
    private final String row = "18";
    private final String column = "B";


    @Test
    @DisplayName("localization - to DTO test")
    void toDTOTest() {
        // GIVEN
        var localization = new Localization();
        var localizationDTO = new LocalizationDTO(id, cemetery, quarter, row, column);
        // WHEN
        localization.setId(id);
        localization.setCemetery(cemetery);
        localization.setQuarter(quarter);
        localization.setLocalizationRow(row);
        localization.setLocalizationColumn(column);
        // THEN
        assertEquals(localizationDTO, localizationMapper.toDTO(localization));
    }

    @Test
    @DisplayName("localization - to Model test")
    void toModelTest() {
        // GIVEN
        var localization = new Localization();
        var localizationDTO = new LocalizationDTO(id, cemetery, quarter, row, column);
        // WHEN
        localization.setId(id);
        localization.setCemetery(cemetery);
        localization.setQuarter(quarter);
        localization.setLocalizationRow(row);
        localization.setLocalizationColumn(column);
        // THEN
        assertEquals(localization, localizationMapper.toModel(localizationDTO));
    }
}
