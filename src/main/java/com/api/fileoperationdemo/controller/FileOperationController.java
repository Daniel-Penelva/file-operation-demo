package com.api.fileoperationdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.fileoperationdemo.service.FileStorageService;

@RestController
@RequestMapping("/images")
public class FileOperationController {

    @Autowired
    FileStorageService fileStorageService;

    // http://localhost:8282/images
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = fileStorageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    // http://localhost:8282/images/{filename}
    @GetMapping("/{filename}")
    public ResponseEntity<?> downloadImage(@PathVariable String filename) {
        byte[] imageInbytes = fileStorageService.downloadImage(filename);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png")).body(imageInbytes);

    }

}
