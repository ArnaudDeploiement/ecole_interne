package com.ecole.interne.ecole.interne.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fname;

    private String lname;


    @ManyToOne
    private Cohort cohort;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}






