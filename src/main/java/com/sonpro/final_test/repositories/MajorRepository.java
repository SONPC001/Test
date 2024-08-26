package com.sonpro.final_test.repositories;

import com.sonpro.final_test.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MajorRepository extends JpaRepository<Major, UUID> {
}