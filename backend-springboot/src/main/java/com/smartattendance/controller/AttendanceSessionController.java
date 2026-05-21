package com.smartattendance.controller;

import com.smartattendance.dto.AttendanceSessionResponse;

import com.smartattendance.service.AttendanceSessionService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/teacher")

@CrossOrigin("*")

public class AttendanceSessionController {

    @Autowired
    private AttendanceSessionService
            attendanceSessionService;

    @PostMapping("/trigger/{timetableId}")

    public AttendanceSessionResponse
    triggerAttendance(

            @PathVariable Long timetableId
    ) {

        return attendanceSessionService
                .createAttendanceSession(
                        timetableId
                );
    }
}