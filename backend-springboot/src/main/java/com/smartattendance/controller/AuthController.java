package com.smartattendance.controller;

import com.smartattendance.dto.AuthResponse;
import com.smartattendance.dto.LoginRequest;
import com.smartattendance.dto.RegisterRequest;

import com.smartattendance.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/auth")

@CrossOrigin("*")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")

    public AuthResponse register(
            @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")

    public AuthResponse login(
            @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }
}