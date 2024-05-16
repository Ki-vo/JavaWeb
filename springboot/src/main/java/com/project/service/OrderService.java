package com.project.service;

import com.project.pojo.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    void deleteOrder(Integer id);

    List<Order> getAllOrders();

    List<Order> getOrderByCateId(Integer cateId);
}
