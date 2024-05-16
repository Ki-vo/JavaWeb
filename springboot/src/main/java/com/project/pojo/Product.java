package com.project.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    @JsonProperty("cate_id")
    private Integer cateId;
    private String seller;
    private Integer price;
    private Integer rest;
    private String coverImgAddr;
}
