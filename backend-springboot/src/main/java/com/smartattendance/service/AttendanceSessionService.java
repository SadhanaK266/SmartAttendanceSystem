package com.smartattendance.service;

import com.smartattendance.dto.AttendanceSessionResponse;

import com.smartattendance.entity.AttendanceSession;

import com.smartattendance.entity.Timetable;

import com.smartattendance.repository.AttendanceSessionRepository;

import com.smartattendance.repository.TimetableRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class AttendanceSessionService {

    @Autowired
    private AttendanceSessionRepository
            attendanceSessionRepository;

    @Autowired
    private TimetableRepository
            timetableRepository;

    @Autowired
    private UltrasonicTokenService
            ultrasonicTokenService;

    public AttendanceSessionResponse
    createAttendanceSession(
            Long timetableId
    ) {

        Timetable timetable =
                timetableRepository
                        .findById(timetableId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Timetable not found"
                                )
                        );

        String generatedToken =
                ultrasonicTokenService
                        .generateSecureToken(
                                timetableId
                        );

        AttendanceSession session =
                new AttendanceSession();

        session.setTimetable(timetable);

        session.setTokenSalt(generatedToken);

        session.setCreatedAt(
                LocalDateTime.now()
        );

        session.setExpiresAt(
                LocalDateTime.now()
                        .plusMinutes(1)
        );

        session.setStatus("ACTIVE");

        AttendanceSession savedSession =
                attendanceSessionRepository
                        .save(session);

        return new AttendanceSessionResponse(

                savedSession.getId(),

                generatedToken,

                savedSession
                        .getExpiresAt()
                        .toString()
        );
    }
}