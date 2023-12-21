package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Integer kind;
    private String seller;
    private Integer price;
    private Integer rest;
    private MultipartFile coverImg;
    private String coverImgAddr;
    private MultipartFile detailImg;
    private String detailImgAddr;

    public Product(String name, Integer kind, String seller, Integer price, Integer rest, MultipartFile coverImg, MultipartFile detailImg) {
        this.name = name;
        this.kind = kind;
        this.seller = seller;
        this.price = price;
        this.rest = rest;
        this.coverImg = coverImg;
        this.detailImg = detailImg;
    }
}
