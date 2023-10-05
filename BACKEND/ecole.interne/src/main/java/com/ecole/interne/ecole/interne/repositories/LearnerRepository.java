package com.ecole.interne.ecole.interne.repositories;

import com.ecole.interne.ecole.interne.entities.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends JpaRepository<Learner,Integer> {

    Learner findById(Long learnerId);
}
