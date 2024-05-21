package com.project.controller;

import com.project.anno.MyLog;
import com.project.pojo.Result;
import com.project.pojo.Salesman;
import com.project.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class SalesController {
    @Autowired
    private SalesService salesService;

    @MyLog
    @GetMapping("/sales/add")
    public Result addSalesman(@RequestParam("type") Integer type) {
        Salesman salesman = new Salesman("1234", type);
        log.info("销售添加:{}", salesman);
        try {
            salesService.addSales(salesman);
            return Result.success();
        } catch (Exception e) {
            return Result.error("销售添加失败");
        }

    }

    @GetMapping("/sales/list")
    public Result getAllSalesman() {
        try {
            List<Salesman> list = salesService.getAllSales();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/sales")
    public Result getById(@RequestParam("id") String name) {
        Integer id = Integer.parseInt(name);
        Salesman res = salesService.getById(id);
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error("不存在该销售");
        }
    }

    @GetMapping("/sales/type")
    public Result getTypeById(@RequestParam("id") String name) {
        Integer id = Integer.parseInt(name);
        Salesman res = salesService.getById(id);
        if (res != null) {
            return Result.success(res.getType());
        } else {
            return Result.error("不存在该销售");
        }
    }

    @MyLog
    @GetMapping("/sales/del")
    public Result delSalesmanById(@RequestParam("id") String name) {
        try {
            Integer id = Integer.parseInt(name);
            salesService.delSales(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }
}