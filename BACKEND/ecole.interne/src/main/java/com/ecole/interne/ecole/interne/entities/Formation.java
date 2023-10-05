package com.ecole.interne.ecole.interne.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cohort_id")
    private Cohort cohort;


    @OneToMany( mappedBy ="formation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;


    public Formation() {
    }
}
