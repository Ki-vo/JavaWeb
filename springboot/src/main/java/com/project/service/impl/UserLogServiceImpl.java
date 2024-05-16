package com.project.service.impl;

import com.project.mapper.SalesMapper;
import com.project.mapper.UserLogMapper;
import com.project.pojo.UserLog;
import com.project.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;
    @Autowired
    private SalesMapper salesMapper;

    @Override
    public void addUserLog(UserLog userLog) {
        userLog.setTimeStamp(LocalDateTime.now());
        if (userLog.getBrowseDuration() != null) {
            userLog.setAction(0);
            userLogMapper.addBrowserLog(userLog);
        } else if (userLog.getPurchaseSum() != null) {
            userLog.setAction(1);
            userLogMapper.addPurchaseLog(userLog);
        } else {
            throw new RuntimeException("UserLog Error!");
        }
    }

    @Override
    public List<UserLog> getUserLogByActionAndSalesman(Integer action, String userId) {
        Integer id = Integer.parseInt(userId);
        Integer cateId = salesMapper.getTypeById(id);
        return userLogMapper.getLogByAction(action, cateId);
    }
}
