package com.sonpro.final_test.services.impl;

import com.sonpro.final_test.repositories.MajorFacilityRepository;
import com.sonpro.final_test.services.MajorFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorFacilityServiceImpl implements MajorFacilityService {
    @Autowired
    private MajorFacilityRepository repository;
}