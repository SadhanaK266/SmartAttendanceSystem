package com.smartattendance.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.smartattendance.entity.Role;

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

        try {

            String fileName =

                    System.currentTimeMillis()

                            + "_"

                            +

                            request
                                    .getProfileImage()
                                    .getOriginalFilename();

            Path uploadPath =
                    Paths.get("uploads");

            if(

                    !Files.exists(uploadPath)

            ) {

                Files.createDirectories(
                        uploadPath
                );
            }

            Path filePath =

                    uploadPath.resolve(
                            fileName
                    );

            Files.copy(

                    request
                            .getProfileImage()
                            .getInputStream(),

                    filePath,

                    StandardCopyOption
                            .REPLACE_EXISTING
            );

            User user = new User();

            user.setName(
                    request.getName()
            );

            user.setEmail(
                    request.getEmail()
            );

            user.setPassword(

                    passwordEncoder.encode(

                            request.getPassword()
                    )
            );

            user.setRole(

                    Role.valueOf(
                            request.getRole()
                    )
            );

            user.setProfileImageUrl(
                    filePath.toString()
            );

            userRepository.save(user);

            String token =

                    jwtService.generateToken(
                            user.getEmail()
                    );

            return new AuthResponse(

                    token,

                    user.getRole().name()
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }

    public AuthResponse login(

            LoginRequest request

    ) {

        User user = userRepository

                .findByEmail(
                        request.getEmail()
                )

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

        return new AuthResponse(

                token,

                user.getRole().name()
        );
    }
}