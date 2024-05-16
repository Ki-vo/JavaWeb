package com.project.controller;

import com.project.pojo.LoginLog;
import com.project.pojo.Result;
import com.project.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class LoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/login/log/add")
    public Result addLoginLog(LoginLog loginLog) {
        try {
            loginLogService.addLoginLog(loginLog);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
