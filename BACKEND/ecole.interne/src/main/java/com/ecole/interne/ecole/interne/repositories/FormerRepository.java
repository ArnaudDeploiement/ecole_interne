package com.ecole.interne.ecole.interne.repositories;

import com.ecole.interne.ecole.interne.entities.Former;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormerRepository extends JpaRepository <Former,Integer> {


    Former findById(Long formerId);
}