package com.project.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileService {

    String saveImg(MultipartFile file, String dir);

    void deleteImg(String path);

//    MultipartFile getImage(String path) throws FileNotFoundException;
}
