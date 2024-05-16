package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String userId;
    private Integer productId;
    private Integer productPrice;
    private Integer purchaseNum;
    private LocalDateTime timeStamp;
}
