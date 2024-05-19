package com.project.service;

import com.project.pojo.OperateLog;

import java.util.List;

public interface OperateLogService {
    List<OperateLog> getAllOperateLog();

    void deleteOperateLogById(Integer id);
}
