package com.smartattendance.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smartattendance.service.FaceRecognitionService;

@RestController

@RequestMapping("/api/student")

@CrossOrigin("*")

public class FaceRecognitionController {

    @Autowired
    private FaceRecognitionService
            faceRecognitionService;

    @PostMapping("/verify-face")

    public String verifyFace(

            @RequestParam("liveImage")
            MultipartFile liveImage,

            @RequestParam("profileImage")
            MultipartFile profileImage

    ) {

        try {

            File liveFile =
                    File.createTempFile(
                            "live",
                            ".jpg"
                    );

            File profileFile =
                    File.createTempFile(
                            "profile",
                            ".jpg"
                    );

            liveImage.transferTo(liveFile);

            profileImage.transferTo(profileFile);

            String response =

                    faceRecognitionService
                            .verifyFace(
                                    liveFile,
                                    profileFile
                            );

            liveFile.delete();
            profileFile.delete();

            return response;

        } catch(Exception e) {

            return e.getMessage();
        }
    }
}