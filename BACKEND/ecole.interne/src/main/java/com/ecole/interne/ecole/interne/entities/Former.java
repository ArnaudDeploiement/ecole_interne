package com.ecole.interne.ecole.interne.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Former {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String lname;

    private String fname;

    @ManyToOne
    private Cohort cohort;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;





    public Former() {

    }

}
