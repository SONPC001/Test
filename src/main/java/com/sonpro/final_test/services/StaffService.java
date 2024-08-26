package com.sonpro.final_test.services;


import com.sonpro.final_test.dto.request.StaffRequest;
import com.sonpro.final_test.entities.Department;
import com.sonpro.final_test.entities.Facility;
import com.sonpro.final_test.entities.Major;
import com.sonpro.final_test.entities.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StaffService {
    List<Staff> getAllStaff();
    Page<Staff> getAllPageStaff(Pageable pageable);
    Staff getStaffById(UUID id);
    Staff createStaff(StaffRequest request);
    Staff updateStaff(StaffRequest request);
    void deleteStaffById(UUID id);

    StaffRequest findStaffById(UUID id);
    Staff update_tt(StaffRequest request);

    boolean existsByStaffCode(String staffCode);
    boolean existsByAccountFe(String accountFe);
    boolean existsByAccountFpt(String accountFpt);

}
