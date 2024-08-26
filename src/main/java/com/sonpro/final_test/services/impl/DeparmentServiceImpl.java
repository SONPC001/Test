package com.sonpro.final_test.services.impl;

import com.sonpro.final_test.entities.Department;
import com.sonpro.final_test.repositories.DepartmentRepository;
import com.sonpro.final_test.services.DeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeparmentServiceImpl implements DeparmentService {
    @Autowired
    private DepartmentRepository repository;

    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
