package com.smartattendance.service;

import com.smartattendance.config.JwtService;
import com.smartattendance.dto.AuthResponse;
import com.smartattendance.dto.LoginRequest;
import com.smartattendance.dto.RegisterRequest;

import com.smartattendance.entity.User;

import com.smartattendance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse register(
            RegisterRequest request
    ) {

        User user = new User();

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(request.getRole());

        userRepository.save(user);

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return new AuthResponse(token);
    }

    public AuthResponse login(
            LoginRequest request
    ) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if(!matches) {
            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return new AuthResponse(token);
    }
}