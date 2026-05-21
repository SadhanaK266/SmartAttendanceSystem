package com.smartattendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AttendanceSubmissionRequest {

    private Long sessionId;

    private String token;
}