package com.smartattendance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_sessions")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AttendanceSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    private String tokenSalt;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private String status;
}