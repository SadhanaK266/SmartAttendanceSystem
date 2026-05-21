package com.smartattendance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private String buildingName;
}