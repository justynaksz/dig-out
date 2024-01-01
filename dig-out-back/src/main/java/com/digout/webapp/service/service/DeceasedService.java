package com.digout.webapp.service.service;

import com.digout.webapp.repository.cemetery.model.Deceased;
import com.digout.webapp.repository.cemetery.repository.DeceasedRepository;
import com.digout.webapp.service.DTO.DeceasedDTO;
import com.digout.webapp.service.mapper.DeceasedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeceasedService {

    private final DeceasedRepository deceasedRepository;
    private final DeceasedMapper deceasedMapper;

    @Autowired

    public DeceasedService(DeceasedRepository deceasedRepository, DeceasedMapper deceasedMapper) {
        this.deceasedRepository = deceasedRepository;
        this.deceasedMapper = deceasedMapper;
    }

    public List<DeceasedDTO> getByBirthDateAnniversary() {
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceased : deceasedRepository.findByBirthDateAnniversary()) {
            deceasedList.add(deceasedMapper.toDTO(deceased));
        }
        return deceasedList;
    }

    public List<DeceasedDTO> getByDeathDateAnniversary() {
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceased : deceasedRepository.findByDeathDateAnniversary()) {
            deceasedList.add(deceasedMapper.toDTO(deceased));
        }
        return deceasedList;
    }

    public List<DeceasedDTO> getAll() {
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceased : deceasedRepository.findAll()) {
            deceasedList.add(deceasedMapper.toDTO(deceased));
        }
        return deceasedList;
    }

    public DeceasedDTO getById(int id) {
        return deceasedMapper.toDTO(deceasedRepository.findById(id));
    }

    public List<DeceasedDTO> getByParams(DeceasedDTO deceasedDTO) {
        var deceased = deceasedMapper.toModel(deceasedDTO);
        var deceasedList = new ArrayList<DeceasedDTO>();
        for (Deceased deceasedByParam : deceasedRepository.findByParams(deceased)) {
            deceasedList.add(deceasedMapper.toDTO(deceasedByParam));
        }
        return deceasedList;
    }

    public void delete(int id) {
        deceasedRepository.deleteById(id);
    }

    public DeceasedDTO save(DeceasedDTO deceasedDTO) {
        var deceased = deceasedMapper.toModel(deceasedDTO);
        return deceasedMapper.toDTO(deceasedRepository.save(deceased));
    }
}
