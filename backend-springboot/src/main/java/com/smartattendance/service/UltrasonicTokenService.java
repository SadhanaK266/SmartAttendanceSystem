package com.smartattendance.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service

public class UltrasonicTokenService {

    public String generateSecureToken(
            Long timetableId
    ) {

        String randomPart =
                UUID.randomUUID().toString();

        String timestamp =
                LocalDateTime.now().toString();

        return timetableId
                + "_"
                + timestamp
                + "_"
                + randomPart;
    }
}