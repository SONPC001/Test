package com.sonpro.final_test.services;

import com.sonpro.final_test.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DeparmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(UUID id);
}
