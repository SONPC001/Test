package com.sonpro.final_test.services.impl;

import com.sonpro.final_test.entities.Facility;
import com.sonpro.final_test.repositories.FacilityRepository;
import com.sonpro.final_test.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private FacilityRepository repository;

    @Override
    public List<Facility> getAllFacilities() {
        return repository.findAll();
    }

    @Override
    public Facility getFacilityById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
