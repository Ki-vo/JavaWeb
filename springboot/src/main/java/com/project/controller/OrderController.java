package com.project.controller;

import com.alibaba.fastjson.JSON;
import com.project.anno.Log;
import com.project.pojo.Order;
import com.project.pojo.Product;
import com.project.pojo.Result;
import com.project.service.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    //获取付款状态
    @PostMapping("/order/pay")
    public Result getPayStatus() {
        try {
            return Result.success("success");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/order/add")
    public Result addOrder(@RequestBody List<Order> orders) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime timeStamp = LocalDateTime.now();

            String username = orders.get(0).getUserId();
            String email = userService.getEmailByUsername(username);
            StringBuilder content = new StringBuilder("用户" + username + ":\n");
            content.append("您于").append(timeStamp.format(dtf)).append("在购物平台购买了如下商品:\n");

            for (Order order : orders) {
                log.info(orders.toString());
                String productName = productService.getById(order.getProductId()).getName();
                order.setTimeStamp(timeStamp);
                orderService.addOrder(order);
                content.append(String.format("%s 数量:%d\n", productName, order.getPurchaseNum()));
            }
            emailService.sendEmail(email, "订单信息", content.toString());
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/order/delete")
    public Result deleteOrder(@RequestParam("id") Integer id) {
        try {
            orderService.deleteOrder(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/order/list/all")
    public Result getAllOrders() {
        try {
            List<Order> list = orderService.getAllOrders();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

//    @GetMapping("/order/list")
//    public Result getOrdersBySale(@RequestParam("username") String username) {
//        Integer id = Integer.parseInt(username);
//        try {
//            Integer cateId = salesService.getById(id).getType();
//            List<Order> list = orderService.getOrderByCateId(cateId);
//            return Result.success(list);
//        } catch (Exception e) {
//            return Result.error(e.getMessage());
//        }
//    }

    @GetMapping("/order/list")
    public Result getOrdersByCateId(@RequestParam("cateId") Integer cateId) {
        try {
            List<Order> list = orderService.getOrderByCateId(cateId);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
