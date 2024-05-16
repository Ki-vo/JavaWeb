package com.project.service;

import com.project.pojo.UserLog;

import java.util.List;

public interface UserLogService {
    void addUserLog(UserLog userLog);

    List<UserLog> getUserLogByActionAndSalesman(Integer action, String userId);
}
