package com.sonpro.final_test.repositories;

import com.sonpro.final_test.entities.StaffMajorFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffMajorFacilityRepository extends JpaRepository<StaffMajorFacility, UUID> {
    List<StaffMajorFacility> findByIdStaff_Id(UUID idStaff);
}