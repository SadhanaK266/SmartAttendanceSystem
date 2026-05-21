package com.smartattendance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_records")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private AttendanceSession session;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    private LocalDateTime timestamp;

    private String status;
}