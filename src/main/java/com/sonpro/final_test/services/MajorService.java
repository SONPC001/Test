package com.sonpro.final_test.services;


import com.sonpro.final_test.entities.Major;

import java.util.List;
import java.util.UUID;

public interface MajorService {
    List<Major> getAllMajors();
    Major getMajorById(UUID id);
}
