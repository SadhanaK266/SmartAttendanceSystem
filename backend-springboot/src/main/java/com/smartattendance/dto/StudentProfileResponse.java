package com.smartattendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class StudentProfileResponse {

    private String name;

    private String email;

    private String profileImageUrl;

    private String role;
}