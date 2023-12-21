package com.project.service.impl;

import com.project.mapper.ProductMapper;
import com.project.pojo.Product;
import com.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getAddr(Product product) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String filename = timestamp + ".png";

        MultipartFile coverImg = product.getCoverImg();
        MultipartFile detailImg = product.getDetailImg();

        try {
            String path = System.getProperty("user.dir") + "\\cover_image\\";
            File targetFile = new File(path + filename);
            coverImg.transferTo(targetFile);
            System.out.println("保存成功" + path + filename);
            product.setCoverImgAddr(path + filename);
        } catch (IOException e) {
            System.out.println("保存失败" + e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            String path = System.getProperty("user.dir") + "\\detail_image\\";
            File targetFile = new File(path + filename);
            detailImg.transferTo(targetFile);
            System.out.println("保存成功" + path + filename);
            product.setDetailImgAddr(path + filename);
        } catch (IOException e) {
            System.out.println("保存失败" + e.getMessage());
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    @Override
    public List<Product> getAllProduct() {
        try {
            return productMapper.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(Integer id) {
        return productMapper.getById(id);
    }

    @Override
    public void delById(Integer id) {
        try {
            productMapper.delById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
