package com.project.service.impl;

import com.project.mapper.ProductMapper;
import com.project.pojo.Product;
import com.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        try {
            productMapper.updateProduct(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public List<Product> getProductByType(Integer type) {
        try {
            return productMapper.getListByType(type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getHotProduct() {
        try {
            return productMapper.getHotProduct();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(Integer id) {
        try {
            return productMapper.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getImgById(Integer id) {
        return productMapper.getCoverImgAddr(id);
    }

    @Override
    public void deleteProductById(Integer id) {
        try {
            productMapper.delById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}