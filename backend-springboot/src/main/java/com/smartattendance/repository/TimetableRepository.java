package com.smartattendance.repository;

import com.smartattendance.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository
        extends JpaRepository<Timetable, Long> {
}