package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.Formation;
import com.ecole.interne.ecole.interne.repositories.FormationRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {


    private final FormationRepository formationcrud;

    public FormationController(FormationRepository formationcrud) {
        this.formationcrud = formationcrud;
    }


    @GetMapping
    public List<Formation> getAllFormation() {
        List<Formation> formationList = formationcrud.findAll();
        return formationList;
    }

    @GetMapping("/{formationId}")
    public Formation getformationById(@PathVariable Long formationId) {
        return formationcrud.findById(formationId);

    }
    @Transactional
    @PostMapping
    public Formation createFormation(@RequestBody Formation formation) {
        return formationcrud.save(formation);
    }

    @PutMapping("/{formationId}")
    public Formation updateFormation(@PathVariable Long formationId, @RequestBody Formation formation) {
        Formation existingFormation = formationcrud.findById(formationId);

        if (formation.getName() != null) {
            existingFormation.setName(formation.getName());
        }

        if (formation.getCohort() != null) {
            existingFormation.setCohort(formation.getCohort());
        }

        if (formation.getCourses() != null) {
            existingFormation.getCourses().addAll(formation.getCourses());
        }

        formationcrud.save(existingFormation);
        return existingFormation;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        formationcrud.deleteById(id);
    }

}






