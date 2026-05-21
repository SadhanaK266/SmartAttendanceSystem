package com.smartattendance.service;

import com.smartattendance.dto.AttendanceSubmissionRequest;

import com.smartattendance.entity.AttendanceRecord;
import com.smartattendance.entity.AttendanceSession;
import com.smartattendance.entity.User;

import com.smartattendance.repository.AttendanceRecordRepository;
import com.smartattendance.repository.AttendanceSessionRepository;
import com.smartattendance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class AttendanceValidationService {

    @Autowired
    private AttendanceSessionRepository
            attendanceSessionRepository;

    @Autowired
    private AttendanceRecordRepository
            attendanceRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public String validateAttendance(

            AttendanceSubmissionRequest request,

            Authentication authentication
    ) {

        String email =
                authentication.getName();

        User student =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Student not found"
                                )
                        );

        AttendanceSession session =
                attendanceSessionRepository
                        .findById(request.getSessionId())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Session not found"
                                )
                        );

        // CHECK TOKEN

        if(
                !session.getTokenSalt()
                        .equals(request.getToken())
        ) {

            throw new RuntimeException(
                    "Invalid Token"
            );
        }

        // CHECK EXPIRY

        if(
                LocalDateTime.now()
                        .isAfter(
                                session.getExpiresAt()
                        )
        ) {

            throw new RuntimeException(
                    "Attendance Session Expired"
            );
        }

        // CHECK DUPLICATE ATTENDANCE

        boolean alreadyMarked =

                attendanceRecordRepository
                        .existsBySessionIdAndStudentId(

                                session.getId(),

                                student.getId()
                        );

        if(alreadyMarked) {

            throw new RuntimeException(
                    "Attendance Already Marked"
            );
        }

        // SAVE ATTENDANCE

        AttendanceRecord record =
                new AttendanceRecord();

        record.setSession(session);

        record.setStudent(student);

        record.setTimestamp(
                LocalDateTime.now()
        );

        record.setStatus("PRESENT");

        attendanceRecordRepository
                .save(record);

        return "Attendance Marked Successfully";
    }
}