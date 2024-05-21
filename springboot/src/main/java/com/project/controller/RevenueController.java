package com.project.controller;

import com.project.pojo.Result;
import com.project.pojo.Revenue;
import com.project.service.RevenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class RevenueController {
    @Autowired
    private RevenueService revenueService;

    @GetMapping("/revenue/total")
    public Result getTotalRevenue() {
        try {
            List<Revenue> res = revenueService.getTotalRevenue();
            return Result.success(res);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
