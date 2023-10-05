package com.ecole.interne.ecole.interne.repositories;

import com.ecole.interne.ecole.interne.entities.Formation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Integer> {
    Formation findById(Long formationId);
}
