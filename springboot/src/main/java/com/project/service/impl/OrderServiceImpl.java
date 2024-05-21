package com.project.service.impl;

import com.project.mapper.OrderMapper;
import com.project.pojo.Order;
import com.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addOrder(Order order) {
        try {
            orderMapper.addOrder(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(Integer id) {
        try {
            orderMapper.deleteOrder(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            return orderMapper.getAllOrders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getOrderByCateId(Integer cateId) {
        try {
            return orderMapper.getOrdersByCateId(cateId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
