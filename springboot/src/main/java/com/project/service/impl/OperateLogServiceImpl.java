package com.project.service.impl;

import com.project.mapper.OperateLogMapper;
import com.project.pojo.OperateLog;
import com.project.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public List<OperateLog> getAllOperateLog() {
        try {
            return operateLogMapper.getAllOperateLog();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOperateLogById(Integer id) {
        try {
            operateLogMapper.deleteOperateLogById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}