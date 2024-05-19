package com.project.controller;

import com.project.anno.Log;
import com.project.pojo.Admin;
import com.project.pojo.Result;
import com.project.pojo.Salesman;
import com.project.service.AdminService;
import com.project.service.SalesService;
import com.project.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    @Log
    @PostMapping("/admin/login")
    public Result adminLogin(@RequestBody Admin admin) {

        admin.setId(Integer.parseInt(admin.getUsername()));
        log.info("管理员登录：{}", admin);
        Admin e = adminService.loginVerify(admin);

        //登录成功
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());

            String jwt = JWTUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        //登录失败
        return Result.error("ID或密码错误");
    }

    @Log
    @GetMapping("/admin/exit")
    public void exit() {
    }
}
