package com.sonpro.final_test.services;

import com.sonpro.final_test.entities.Facility;

import java.util.List;
import java.util.UUID;

public interface FacilityService {
    List<Facility> getAllFacilities();
    Facility getFacilityById(UUID id);
}
