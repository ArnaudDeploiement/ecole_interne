package com.ecole.interne.ecole.interne.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate datedebut;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate datefin;

    private LocalTime horairedebut;

    private LocalTime horairefin;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    public Session() {
    }

    public Session(LocalDate datedebut, LocalDate datefin, LocalTime horairedebut, LocalTime horairefin, Course course) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.horairedebut = horairedebut;
        this.horairefin = horairefin;
        this.course = course;
    }
}

