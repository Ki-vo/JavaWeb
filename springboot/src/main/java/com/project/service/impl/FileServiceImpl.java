package com.project.service.impl;

import com.project.service.FileService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Transactional
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String saveImg(MultipartFile file) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String filename = timestamp + ".png";

        try {
            String path = System.getProperty("user.dir") + File.separator + "cover_image" + File.separator;
            File targetFile = new File(path + filename);
            file.transferTo(targetFile);
            log.info("保存成功{}{}", path, filename);
            return path + filename;
        } catch (IOException e) {
            log.info("保存失败{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteImg(String path) {
        File file = new File(path);

        // 尝试删除文件
        if (file.exists()) {
            if (file.delete()) {
                log.info("文件已删除:{}", path);
            } else {
                log.info("文件删除失败:{}", path);
                throw new RuntimeException("Delete Failed!");
            }
        } else {
            log.info("文件不存在:{}", path);
            throw new RuntimeException("File Not Found!");
        }
    }
}
