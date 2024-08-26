package com.sonpro.final_test.services.impl;

import com.sonpro.final_test.entities.Major;
import com.sonpro.final_test.repositories.MajorRepository;
import com.sonpro.final_test.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    @Override
    public Major getMajorById(UUID id) {
        return majorRepository.findById(id).orElse(null);
    }
}
