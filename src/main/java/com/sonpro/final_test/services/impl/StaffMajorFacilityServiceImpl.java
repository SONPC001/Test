package com.sonpro.final_test.services.impl;

import com.sonpro.final_test.entities.*;
import com.sonpro.final_test.repositories.StaffMajorFacilityRepository;
import com.sonpro.final_test.repositories.DepartmentFacilityRepository;
import com.sonpro.final_test.repositories.MajorFacilityRepository;
import com.sonpro.final_test.services.StaffMajorFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffMajorFacilityServiceImpl implements StaffMajorFacilityService {
    @Autowired
    private StaffMajorFacilityRepository repository;

    @Autowired
    private DepartmentFacilityRepository departmentFacilityRepository;

    @Autowired
    private MajorFacilityRepository majorFacilityRepository;

    @Override
    public List<StaffMajorFacility> getAll() {
        return repository.findAll();
    }

    @Override
    public StaffMajorFacility addBM_CN(Staff staff, Department department, Major major, Facility facility) {
        // Lưu DepartmentFacility trước
        DepartmentFacility df = new DepartmentFacility();
        df.setIdDepartment(department);
        df.setIdFacility(facility);
        df.setIdStaff(staff);
        departmentFacilityRepository.save(df);

        // Sau đó lưu MajorFacility
        MajorFacility mf = new MajorFacility();
        mf.setIdMajor(major);
        mf.setIdDepartmentFacility(df);
        majorFacilityRepository.save(mf);

        // Cuối cùng lưu StaffMajorFacility
        StaffMajorFacility smf = new StaffMajorFacility();
        smf.setIdStaff(staff);
        smf.setIdMajorFacility(mf);

        return repository.save(smf);
    }

    @Override
    public List<StaffMajorFacility> getStaffMajorFacilityByStaff_Id(UUID idStaff) {
        return repository.findByIdStaff_Id(idStaff);
    }
}
