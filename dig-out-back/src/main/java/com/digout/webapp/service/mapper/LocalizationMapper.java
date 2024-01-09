package com.digout.webapp.service.mapper;

import com.digout.webapp.repository.model.Localization;
import com.digout.webapp.service.DTO.LocalizationDTO;
import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {

    public Localization toModel(LocalizationDTO localizationDTO) {
        var id = localizationDTO.getId();
        var cemetery = localizationDTO.getCemetery();
        var quarter = localizationDTO.getQuarter();
        var localizationRow = localizationDTO.getLocalizationRow();
        var localizationColumn = localizationDTO.getLocalizationColumn();
        return new Localization(id, cemetery, quarter, localizationRow, localizationColumn);
    }

    public LocalizationDTO toDTO(Localization localization) {
        var id = localization.getId();
        var cemetery = localization.getCemetery();
        var quarter = localization.getQuarter();
        var localizationRow = localization.getLocalizationRow();
        var localizationColumn = localization.getLocalizationColumn();
        return new LocalizationDTO(id, cemetery, quarter, localizationRow, localizationColumn);
    }
}
