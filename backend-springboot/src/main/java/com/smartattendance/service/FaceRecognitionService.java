package com.smartattendance.service;

import org.springframework.http.*;

import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;

import org.springframework.util.MultiValueMap;

import org.springframework.web.client.RestTemplate;

import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Service

public class FaceRecognitionService {

    public String verifyFace(

            File liveImage,

            File profileImage
    ) {

        String pythonApiUrl =
                "http://127.0.0.1:8000/verify-face";

        RestTemplate restTemplate =
                new RestTemplate();

        MultiValueMap<String, Object> body =
                new LinkedMultiValueMap<>();

        body.add(
                "live_image",

                new FileSystemResource(liveImage)
        );

        body.add(
                "profile_image",

                new FileSystemResource(profileImage)
        );

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.MULTIPART_FORM_DATA
        );

        HttpEntity<MultiValueMap<String, Object>>
                requestEntity =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =

                restTemplate.postForEntity(

                        pythonApiUrl,

                        requestEntity,

                        String.class
                );

        return response.getBody();
    }
}