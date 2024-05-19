package com.project.controller;

import com.project.anno.Log;
import com.project.pojo.Result;
import com.project.pojo.User;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
public class DeregisterController {
    @Autowired
    private UserService userService;

    @Log
    @PostMapping("/user/delete")
    public Result deregister(@RequestBody User user) {
        User u = userService.loginVerify(user);
        if (u != null) {
            try {
                userService.deregister(user);
                return Result.success();
            } catch (Exception e) {
                return Result.error("删除失败");
            }
        }
        return Result.error("密码错误");
    }
}
