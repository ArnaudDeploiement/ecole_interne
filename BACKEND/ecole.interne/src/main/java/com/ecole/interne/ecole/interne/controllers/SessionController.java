package com.ecole.interne.ecole.interne.controllers;


import com.ecole.interne.ecole.interne.entities.Session;
import com.ecole.interne.ecole.interne.repositories.SessionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {


    private final SessionRepository sessioncrud;

    public SessionController(SessionRepository sessioncrud) {
        this.sessioncrud = sessioncrud;
    }


    @GetMapping
    public List<Session> getAllSession() {
        List<Session> sessionList = sessioncrud.findAll();
        return sessionList;
    }

    @GetMapping("/{sessionID}")
    public Session getSessionById(@PathVariable Long sessionID) {
        return sessioncrud.findById(sessionID);

    }
    @Transactional
    @PostMapping
    public Session createSession(@RequestBody Session session) {
        session.setDatedebut(session.getDatedebut());
        session.setDatefin(session.getDatefin());
        session.setHorairedebut(session.getHorairedebut());
        session.setHorairefin(session.getHorairefin());
        return sessioncrud.save(session);
    }


    @PutMapping("/{sessionId}")
    public Session updateSession(@PathVariable Long sessionId, @RequestBody Session session) {
        Session existingSession = sessioncrud.findById(sessionId);

        if (session.getDatedebut() != null) {
            existingSession.setDatedebut(session.getDatedebut());
        }

        if (session.getDatefin() != null) {
            existingSession.setDatefin(session.getDatefin());
        }

        if (session.getCourse() != null) {
            existingSession.setCourse(session.getCourse());
        }

        if (session.getHorairedebut() != null) {
            existingSession.setHorairedebut(session.getHorairedebut());
        }

        if (session.getHorairefin() != null) {
            existingSession.setHorairefin(session.getHorairefin());
        }

        sessioncrud.save(existingSession);
        return existingSession;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        sessioncrud.deleteById(id);
    }

}
