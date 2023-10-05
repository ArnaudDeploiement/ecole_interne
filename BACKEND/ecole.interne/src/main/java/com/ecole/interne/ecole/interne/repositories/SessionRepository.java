package com.ecole.interne.ecole.interne.repositories;


import com.ecole.interne.ecole.interne.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository  extends JpaRepository<Session,Integer> {
    Session findById(Long sessionID);
}
