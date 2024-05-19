package com.project.service.impl;

import com.project.service.FileService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Transactional
@Service
public class FileServiceImpl implements FileService {

    private static final String COVER_IMAGES_DIR = System.getProperty("user.dir") + File.separator + "cover_image" + File.separator;

    @Override
    public String saveImg(MultipartFile file, String dir) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String filename = timestamp + ".png";

        try {
            File targetFile = new File(dir + filename);
            file.transferTo(targetFile);
            log.info("图片保存成功:{}{}", dir, filename);
            return filename;
        } catch (IOException e) {
            log.info("图片保存失败:{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteImg(String path) {
        File file = new File(path);

        // 尝试删除文件
        if (file.exists()) {
            if (file.delete()) {
                log.info("图片已删除:{}", path);
            } else {
                log.info("图片删除失败:{}", path);
                throw new RuntimeException("Delete Failed!");
            }
        } else {
            log.info("目标图片不存在:{}", path);
            throw new RuntimeException("File Not Found!");
        }
    }

//    @Override
//    public MultipartFile getImage(String imageName) throws FileNotFoundException {
//        String path = COVER_IMAGES_DIR + imageName;
//        File file = new File(path);
//        if (!file.exists() && file.isFile() && file.canRead()) {
//            FileInputStream stream = new FileInputStream(file);
//        }
//    }
}
