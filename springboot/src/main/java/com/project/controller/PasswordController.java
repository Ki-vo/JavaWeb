package com.project.controller;

import com.project.anno.Log;
import com.project.pojo.Admin;
import com.project.pojo.Result;
import com.project.pojo.Salesman;
import com.project.pojo.User;
import com.project.service.AdminService;
import com.project.service.SalesService;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class PasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private SalesService salesService;

    private final String defaultSalesPassword = "1234";

    @Log
    @PostMapping("/password/edit")
    public Result editPassword(@RequestParam("id") String username,
                               @RequestParam("tag") String tag,
                               @RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword) {
        switch (tag) {
            case "user": {
                User user = new User();
                user.setUsername(username);
                User res = userService.getByUsername(user);
                if (oldPassword.equals(res.getPassword())) {
                    res.setPassword(newPassword);
                    try {
                        userService.updateUser(res);
                        return Result.success();
                    } catch (Exception e) {
                        return Result.error(e.getMessage());
                    }
                } else {
                    return Result.error("WRONG_PASSWORD");
                }
            }
            case "admin": {
                Integer id = Integer.parseInt(username);
                Admin res = adminService.getById(id);
                if (oldPassword.equals(res.getPassword())) {
                    res.setPassword(newPassword);
                    try {
                        adminService.updateAdmin(res);
                        return Result.success();
                    } catch (Exception e) {
                        return Result.error(e.getMessage());
                    }
                } else {
                    return Result.error("WRONG_PASSWORD");
                }
            }
            case "sales": {
                Integer id = Integer.parseInt(username);
                Salesman res = salesService.getById(id);
                if (oldPassword.equals(res.getPassword())) {
                    res.setPassword(newPassword);
                    try {
                        salesService.updateSales(res);
                        return Result.success();
                    } catch (Exception e) {
                        return Result.error(e.getMessage());
                    }
                } else {
                    return Result.error("WRONG_PASSWORD");
                }
            }
            default:
                return Result.error("WRONG_TAG");
        }
    }

    @GetMapping("/password/reset/sales")
    public Result resetSalesPassword(@RequestParam("id") Integer id) {
        Salesman res = salesService.getById(id);
        res.setPassword(defaultSalesPassword);
        try {
            salesService.updateSales(res);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
