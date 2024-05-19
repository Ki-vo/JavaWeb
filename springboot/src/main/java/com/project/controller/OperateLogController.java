package com.project.controller;

import com.project.anno.Log;
import com.project.pojo.OperateLog;
import com.project.pojo.Result;
import com.project.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class OperateLogController {
    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/log/operate/list")
    public Result getAllOperateLog() {
        try {
            List<OperateLog> res = operateLogService.getAllOperateLog();
            return Result.success(res);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }

    @Log
    @GetMapping("/log/operate/delete")
    public Result deleteOperateLog(@RequestParam("id") Integer id) {
        try {
            operateLogService.deleteOperateLogById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
