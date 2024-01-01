package com.digout.webapp.service.service;

import com.digout.webapp.repository.cemetery.model.GraveOwner;
import com.digout.webapp.repository.cemetery.repository.GraveOwnerRepository;
import com.digout.webapp.service.DTO.GraveOwnerDTO;
import com.digout.webapp.service.mapper.GraveOwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraveOwnerService {

    private final GraveOwnerRepository graveOwnerRepository;
    private final GraveOwnerMapper graveOwnerMapper;

    @Autowired
    public GraveOwnerService(GraveOwnerRepository graveOwnerRepository, GraveOwnerMapper graveOwnerMapper) {
        this.graveOwnerRepository = graveOwnerRepository;
        this.graveOwnerMapper = graveOwnerMapper;
    }

    public List<GraveOwnerDTO> getAll() {
        var graveOwners = new ArrayList<GraveOwnerDTO>();
        for (GraveOwner graveOwner : graveOwnerRepository.findAll()) {
            graveOwners.add(graveOwnerMapper.toDTO(graveOwner));
        }
        return graveOwners;
    }

    public GraveOwnerDTO getById(int id) {
        return graveOwnerMapper.toDTO(graveOwnerRepository.findById(id));
    }

    public void delete(int id) {
        graveOwnerRepository.deleteById(id);
    }

    public GraveOwnerDTO save(GraveOwnerDTO graveOwnerDTO) {
        var graveOwner = graveOwnerMapper.toModel(graveOwnerDTO);
        return graveOwnerMapper.toDTO(graveOwnerRepository.save(graveOwner));
    }
}
