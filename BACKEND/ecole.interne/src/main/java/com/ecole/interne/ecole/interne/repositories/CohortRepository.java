package com.ecole.interne.ecole.interne.repositories;

import com.ecole.interne.ecole.interne.entities.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CohortRepository extends JpaRepository <Cohort,Integer> {
    Cohort findById(Long cohortId);
}
