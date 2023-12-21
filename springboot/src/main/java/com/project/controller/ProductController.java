package com.project.controller;

import com.project.pojo.Product;
import com.project.pojo.Result;
import com.project.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public Result addProduct(@RequestParam("name") String name,
                             @RequestParam("cate_id") Integer kind,
                             @RequestParam("seller") String seller,
                             @RequestParam("price") Integer price,
                             @RequestParam("rest") Integer rest,
                             @RequestParam("cover_img") MultipartFile coverImg,
                             @RequestParam("detail_img") MultipartFile detailImg) {
        Product product = new Product(name, kind, seller, price, rest, coverImg, detailImg);
        log.info("商品添加:{}", product);
        Product newproduct = productService.getAddr(product);
        try {
            productService.addProduct(newproduct);
            return Result.success();
        } catch (Exception e) {
            return Result.error("商品添加失败");
        }

    }

    @PostMapping("/product/list")
    public Result getAllProduct() {
        try {
            List<Product> list = productService.getAllProduct();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/product")
    public Result getById(@RequestParam("id") Integer id) {
        Product res = productService.getById(id);
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error("不存在该商品");
        }
    }

    @GetMapping("/product/del")
    public Result delById(@RequestParam("id") Integer id) {
        try {
            productService.delById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }
}
