package com.smartattendance.controller;

import com.smartattendance.dto.AttendanceSubmissionRequest;

import com.smartattendance.service.AttendanceValidationService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/student")

@CrossOrigin("*")

public class StudentAttendanceController {

    @Autowired
    private AttendanceValidationService
            attendanceValidationService;

    @PostMapping("/mark-attendance")

    public String markAttendance(

            @RequestBody
            AttendanceSubmissionRequest request,

            Authentication authentication
    ) {

        return attendanceValidationService
                .validateAttendance(
                        request,
                        authentication
                );
    }
}