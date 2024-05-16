package com.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String saveImg(MultipartFile file);

    void deleteImg(String path);
}
