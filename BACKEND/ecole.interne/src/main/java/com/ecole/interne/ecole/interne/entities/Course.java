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
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Session> sessions;

    public Course() {
    }

    public Course(String name, Formation formation, List<Session> sessions) {
        this.name = name;
        this.formation = formation;
        this.sessions = sessions;
    }
}
