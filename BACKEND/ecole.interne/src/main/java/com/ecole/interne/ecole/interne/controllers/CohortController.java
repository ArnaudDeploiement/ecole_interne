package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.Cohort;
import com.ecole.interne.ecole.interne.entities.Course;
import com.ecole.interne.ecole.interne.entities.Formation;
import com.ecole.interne.ecole.interne.entities.Session;
import com.ecole.interne.ecole.interne.repositories.CohortRepository;
import com.ecole.interne.ecole.interne.repositories.FormationRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cohort")
public class CohortController {

    private final CohortRepository cohortcrud;

    private final FormationRepository formationcrud;

    public CohortController(CohortRepository cohortcrud, FormationRepository formationcrud) {
        this.cohortcrud = cohortcrud;
        this.formationcrud = formationcrud;
    }


    @GetMapping
    public List<Cohort> getAllCohort() {
        List<Cohort> cohortList = cohortcrud.findAll();
        return cohortList;
    }

    @GetMapping("/{cohortId}")
    public Cohort getCohortById(@PathVariable Long cohortId) {
        return cohortcrud.findById(cohortId);

    }

    @Transactional
    @PostMapping
    public Cohort createCohort(@RequestBody Cohort cohort) {
        List<Formation> formations = cohort.getFormations();

        if (formations != null) {
            for (Formation formation : formations) {
                formation.setCohort(cohort);  // Établir la relation avec la cohorte

                List<Course> courses = formation.getCourses();
                if (courses != null) {
                    for (Course course : courses) {
                        course.setFormation(formation);  // Établir la relation avec la formation

                        List<Session> sessions = course.getSessions();
                        if (sessions != null) {
                            for (Session session : sessions) {
                                session.setCourse(course);  // Établir la relation avec le cours
                                // Vous pouvez enregistrer chaque session ici si nécessaire
                            }
                        }
                    }
                }
            }
        }

        return cohortcrud.save(cohort);
    }

    @PutMapping("/{cohortId}")
    public Cohort updateCohort(@PathVariable Long cohortId, @RequestBody Cohort cohort) {
        Cohort existingCohort = cohortcrud.findById(cohortId);

        if (cohort.getName() != null) {
            existingCohort.setName(cohort.getName());
        }

        if (cohort.getLearners() != null) {
            existingCohort.getLearners().addAll(cohort.getLearners());
        }

        if (cohort.getFormers() != null) {
            existingCohort.getFormers().addAll(cohort.getFormers());
        }

        if (cohort.getFormations() != null) {
            existingCohort.getFormations().addAll(cohort.getFormations());
        }

        return existingCohort;

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        cohortcrud.deleteById(id);
    }

}
