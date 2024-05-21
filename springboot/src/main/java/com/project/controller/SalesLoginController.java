package com.project.controller;

import com.project.anno.MyLog;
import com.project.pojo.Result;
import com.project.pojo.Salesman;
import com.project.service.SalesService;
import com.project.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class SalesLoginController {
    @Autowired
    private SalesService salesService;

    @MyLog
    @PostMapping("/sales/login")
    public Result salesLogin(@RequestBody Salesman salesman) {

        salesman.setId(Integer.parseInt(salesman.getUsername()));
        log.info("销售登录：{}", salesman);
        Salesman e = salesService.loginVerify(salesman);

        //登录成功
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("type", e.getType());

            String jwt = JWTUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        //登录失败
        return Result.error("ID或密码错误");
    }

    @MyLog
    @GetMapping("/sales/exit")
    public void exit() {
    }
}
