package com.ecole.interne.ecole.interne.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity
@Data
public class Cohort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;


    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Formation> formations;


    @OneToMany(mappedBy = "cohort", cascade = CascadeType.PERSIST)
    private List<Former> formers;


    @OneToMany(mappedBy = "cohort", cascade = CascadeType.PERSIST)
    private List<Learner> learners;

    public Cohort() {

    }

    public Cohort(String name, List<Formation> formations, List<Former> formers, List<Learner> learners) {
        this.name = name;
        this.formations = formations;
        this.formers = formers;
        this.learners = learners;
    }


}
