package com.sonpro.final_test.services.impl;

import com.sonpro.final_test.repositories.DepartmentFacilityRepository;
import com.sonpro.final_test.services.DepartmentFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentFacilityServiceImpl implements DepartmentFacilityService {
    @Autowired
    private DepartmentFacilityRepository repository;


}