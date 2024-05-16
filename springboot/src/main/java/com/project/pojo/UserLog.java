package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLog {
    private Integer id;
    private String userId;
    private Integer productId;
    private LocalDateTime timeStamp;
    //action:0 浏览和 1 购买
    private Integer action;
    //action为0时有效，代表购买商品数量
    private Integer purchaseSum;
    //action为1时有效，代表浏览时间
    private Integer browseDuration;
}
