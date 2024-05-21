package com.project.service.impl;

import com.project.mapper.OrderMapper;
import com.project.pojo.Revenue;
import com.project.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Revenue> getTotalRevenue() {
        try {
            return orderMapper.getTotalRevenue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
