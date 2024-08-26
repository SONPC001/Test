package com.sonpro.final_test.services.impl;

import com.sonpro.final_test.dto.request.StaffRequest;
import com.sonpro.final_test.entities.*;
import com.sonpro.final_test.repositories.StaffRepository;
import com.sonpro.final_test.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository repository;

    private Long longDate() {
        // Tạo một Calendar và thiết lập ngày
        Calendar calendar = Calendar.getInstance();
        // Chuyển Calendar thành Date
        Date date = calendar.getTime();
        // Lấy số mili giây từ epoch
        long millis = date.getTime();
        System.out.println("Date in millis: " + millis);
        return millis;
    }

    @Override
    public List<Staff> getAllStaff() {
        return repository.findAll();
    }

    @Override
    public Page<Staff> getAllPageStaff(Pageable pageable) {
        return repository.findAllByOrderByStaffCodeAsc(pageable);
    }

    @Override
    public Staff getStaffById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Staff createStaff(StaffRequest request) {
        Staff s = new Staff();
        s.setCreatedDate(longDate());
        return getStaff(request, s);
    }

    @Override
    public Staff updateStaff(StaffRequest request) {
        Staff s = new Staff();
        s.setId(request.getId());
        s.setCreatedDate(request.getCreatedDate());
        return getStaff(request, s);
    }

    private Staff getStaff(StaffRequest request, Staff s) {
        s.setName(request.getName());
        s.setStaffCode(request.getStaffCode());
        s.setStaffCode(request.getStaffCode());
        s.setAccountFe(request.getAccountFe());
        s.setAccountFpt(request.getAccountFpt());
        s.setStatus(request.getStatus());
        s.setLastModifiedDate(longDate());

        return repository.save(s);
    }

    @Override
    public void deleteStaffById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public StaffRequest findStaffById(UUID id) {
        Staff staff = repository.findById(id).orElse(null);
        StaffRequest request = null;
        if (staff != null) {
            request = new StaffRequest();
            request.setId(staff.getId());
            request.setName(staff.getName());
            request.setStaffCode(staff.getStaffCode());
            request.setAccountFe(staff.getAccountFe());
            request.setAccountFpt(staff.getAccountFpt());
            request.setStatus(staff.getStatus());
            request.setCreatedDate(staff.getCreatedDate());
            request.setLastModifiedDate(staff.getLastModifiedDate());
            return request;
        }
        return request;
    }

    @Override
    public Staff update_tt(StaffRequest request) {
        StaffRequest rq = findStaffById(request.getId());
        if (request.getStatus() != 1) {
            rq.setStatus(Short.parseShort("1"));
        } else {
            rq.setStatus(Short.parseShort("0"));
        }
        return updateStaff(rq);
    }

    @Override
    public boolean existsByStaffCode(String staffCode) {
        return repository.existsByStaffCode(staffCode);
    }

    @Override
    public boolean existsByAccountFe(String accountFe) {
        return repository.existsByAccountFe(accountFe);
    }

    @Override
    public boolean existsByAccountFpt(String accountFpt) {
        return repository.existsByAccountFpt(accountFpt);
    }

}
