package com.smartattendance.repository;

import com.smartattendance.entity.AttendanceRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepository
        extends JpaRepository<
        AttendanceRecord,
        Long
        > {

    boolean existsBySessionIdAndStudentId(

            Long sessionId,

            Long studentId
    );
}