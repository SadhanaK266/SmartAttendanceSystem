package com.smartattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class AttendanceSessionResponse {

    private Long sessionId;

    private String token;

    private String expiresAt;
}