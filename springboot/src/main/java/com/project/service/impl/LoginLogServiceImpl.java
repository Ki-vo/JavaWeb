package com.project.service.impl;

import com.project.mapper.LoginLogMapper;
import com.project.pojo.LoginLog;
import com.project.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void addLoginLog(LoginLog loginLog) {
        try {
            loginLogMapper.addLoginLog(loginLog);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
