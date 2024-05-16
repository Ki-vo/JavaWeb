package com.project.controller;

import com.project.pojo.Result;
import com.project.pojo.UserLog;
import com.project.service.SalesService;
import com.project.service.UserLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class UserLogController {
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private SalesService salesService;

    @PostMapping("/log/add")
    public Result addUserLog(@RequestBody UserLog userLog) {
        try {
            userLogService.addUserLog(userLog);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("log/list")
    public Result listUserLogBySales(@RequestParam("action") String action,
                                     @RequestParam("username") String userId) {
        if (action.equals("BROWSE")) {
            try {
                List<UserLog> logs = userLogService.getUserLogByActionAndSalesman(0, userId);
                return Result.success(logs);
            } catch (Exception e) {
                return Result.error(e.getMessage());
            }
        } else if (action.equals("PURCHASE")) {
            try {
                List<UserLog> logs = userLogService.getUserLogByActionAndSalesman(1, userId);
                return Result.success(logs);
            } catch (Exception e) {
                return Result.error(e.getMessage());
            }
        } else {
            return Result.error("REQUEST_WITHOUT_ACTION");
        }
    }
}
