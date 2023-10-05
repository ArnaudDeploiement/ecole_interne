package com.ecole.interne.ecole.interne.controllers;

import com.ecole.interne.ecole.interne.entities.Course;
import com.ecole.interne.ecole.interne.entities.Formation;
import com.ecole.interne.ecole.interne.entities.Session;
import com.ecole.interne.ecole.interne.repositories.CourseRepository;
import com.ecole.interne.ecole.interne.repositories.FormationRepository;
import com.ecole.interne.ecole.interne.repositories.SessionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository coursecrud;
    private final SessionRepository sessioncrud;

    private final FormationRepository formationcrud;

    public CourseController(CourseRepository coursecrud, SessionRepository sessioncrud, FormationRepository formationcrud) {
        this.coursecrud = coursecrud;
        this.sessioncrud = sessioncrud;
        this.formationcrud = formationcrud;
    }


    @GetMapping
    public List<Course> getAllFormation() {
        List<Course> courseList = coursecrud.findAll();
        return courseList;
    }

    @GetMapping("/{courseId}")
    public Course getformationById(@PathVariable Long courseId) {
        return coursecrud.findById(courseId);

    }
    @Transactional
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return coursecrud.save(course);
    }

    @PutMapping("/{courseID}")
    public Course updateCourse(@PathVariable Long courseID, @RequestBody Course course) {
        Course existingCourse = coursecrud.findById(courseID);

        if (course.getName() != null) {
            existingCourse.setName(course.getName());
        }

        if (course.getSessions() != null) {
            existingCourse.getSessions().addAll(course.getSessions());

        }
        if (course.getFormation() != null) {
            existingCourse.setFormation(course.getFormation());

        }

        coursecrud.save(existingCourse);
        return existingCourse;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        coursecrud.deleteById(id);
    }


}
