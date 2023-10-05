package com.ecole.interne.ecole.interne.repositories;

import com.ecole.interne.ecole.interne.entities.Admin;
import com.ecole.interne.ecole.interne.entities.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findById(Long adminId);
}