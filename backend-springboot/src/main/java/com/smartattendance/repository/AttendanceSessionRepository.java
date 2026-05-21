package com.smartattendance.repository;

import com.smartattendance.entity.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceSessionRepository
        extends JpaRepository<AttendanceSession, Long> {
}