package com.project.controller;

import com.project.pojo.BrowseLog;
import com.project.pojo.Result;
import com.project.service.BrowseLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class BrowseLogController {
    @Autowired
    private BrowseLogService browseLogService;

    @PostMapping("/log/browse/add")
    public Result addBrowseLog(@RequestBody BrowseLog browseLog) {
        try {
            browseLogService.addBrowseLog(browseLog);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/log/browse/list")
    public Result listBrowseLogBySales(@RequestParam("username") String userId) {
        try {
            List<BrowseLog> logs = browseLogService.getBrowseLogBySalesman(userId);
            return Result.success(logs);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
