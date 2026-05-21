package com.smartattendance.repository;

import com.smartattendance.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository
        extends JpaRepository<Course, Long> {
}