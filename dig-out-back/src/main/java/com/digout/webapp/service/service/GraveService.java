package com.digout.webapp.service.service;

import com.digout.webapp.repository.cemetery.model.Grave;
import com.digout.webapp.repository.cemetery.repository.GraveRepository;
import com.digout.webapp.service.DTO.GraveDTO;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.mapper.GraveMapper;
import com.digout.webapp.service.mapper.GraveOwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraveService {

    private final GraveRepository graveRepository;
    private final GraveMapper graveMapper;
    private final GraveOwnerMapper graveOwnerMapper;

    @Autowired

    public GraveService(GraveRepository graveRepository, GraveMapper graveMapper, GraveOwnerMapper graveOwnerMapper) {
        this.graveRepository = graveRepository;
        this.graveMapper = graveMapper;
        this.graveOwnerMapper = graveOwnerMapper;
    }

    public List<GraveDTO> getByGraveOwner(GraveOwnerDTO graveOwnerDTO) {
        var graveOwner = graveOwnerMapper.toModel(graveOwnerDTO);
        var graves = new ArrayList<GraveDTO>();
        for (Grave grave : graveRepository.findByGraveOwner(graveOwner)) {
            graves.add(graveMapper.toDTO(grave));
        }
        return graves;
    }

    public List<GraveDTO> getAvailableByCemetery(String cemetery) {
        var graves = new ArrayList<GraveDTO>();
        for (Grave grave : graveRepository.findAvailableByCemetery(cemetery)) {
            graves.add(graveMapper.toDTO(grave));
        }
        return graves;
    }

    public List<GraveDTO> getAll() {
        var graves = new ArrayList<GraveDTO>();
        for (Grave grave : graveRepository.findAll()) {
            graves.add(graveMapper.toDTO(grave));
        }
        return graves;
    }

    public GraveDTO getById(int id) {
        return graveMapper.toDTO(graveRepository.findById(id));
    }

    public void delete(int id) {
        graveRepository.deleteById(id);
    }

    public GraveDTO save(GraveDTO graveDTO) {
        var grave = graveMapper.toModel(graveDTO);
        return graveMapper.toDTO(graveRepository.save(grave));
    }
}
