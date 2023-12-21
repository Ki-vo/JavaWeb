package com.project.service;

import com.project.pojo.Product;

import java.util.List;

public interface ProductService {
    //    List<Product>
    Product getAddr(Product product);

    void addProduct(Product product);

    List<Product> getAllProduct();

    Product getById(Integer id);

    void delById(Integer id);

}
