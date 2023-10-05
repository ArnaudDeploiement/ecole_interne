package com.ecole.interne.ecole.interne.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    protected String fname;

    protected String lname;

    private String username;

    private String password;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private Learner learner;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,optional = true)
    private Former former;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,optional = true)
    private Admin admin;

    public User(String usernamecreated, String passwordcreate) {
        this.username = usernamecreated;
        this.password = passwordcreate;
    }


    public User(String fname, String lname, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public User(String fname, String lname, String username, String password, Learner learner, Former former, Admin admin) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.learner = learner;
        this.former = former;
        this.admin = admin;
    }
}
