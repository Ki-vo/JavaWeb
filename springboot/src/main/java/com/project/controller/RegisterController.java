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
public class RegisterController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/register")
    public Result register(@RequestBody User user) {
        User search = userService.getByUsername(user);
        if (search != null) {
            return Result.error("EXIST_NAME");
        }
        log.info("新增用户{}", user);
        userService.register(user);
        return Result.success();
    }
}
