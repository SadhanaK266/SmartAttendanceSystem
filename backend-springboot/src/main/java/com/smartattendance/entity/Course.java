package com.smartattendance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;

    private String courseName;
}