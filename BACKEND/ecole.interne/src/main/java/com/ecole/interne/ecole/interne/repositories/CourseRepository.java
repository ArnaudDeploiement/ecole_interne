package com.ecole.interne.ecole.interne.repositories;


import com.ecole.interne.ecole.interne.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {


    Course findById(Long courseID);
}
