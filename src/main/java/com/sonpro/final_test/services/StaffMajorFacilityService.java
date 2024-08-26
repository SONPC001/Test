package com.sonpro.final_test.services;

import com.sonpro.final_test.entities.*;

import java.util.List;
import java.util.UUID;


public interface StaffMajorFacilityService {
    List<StaffMajorFacility> getAll();
    StaffMajorFacility addBM_CN(Staff staff, Department department, Major major, Facility facility);
    List<StaffMajorFacility> getStaffMajorFacilityByStaff_Id(UUID idStaff);
}