package com.project.service;

import com.project.pojo.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    void updateProduct(Product product);

    List<Product> getAllProduct();

    List<Product> getProductByType(Integer type);

    List<Product> getHotProduct();

    Product getProductById(Integer id);

    String getImgById(Integer id);

    void deleteProductById(Integer id);

}
