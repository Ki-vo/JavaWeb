package com.project.controller;

import com.project.pojo.Result;
import com.project.pojo.User;
import com.project.service.UserService;
import com.project.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录：{}", user);
        User e = userService.loginVerify(user);

        //登录成功
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getUsername());

            String jwt = JWTUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        //登录失败
        return Result.error("用户名或密码错误");
    }
}
