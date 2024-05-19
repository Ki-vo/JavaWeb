package com.project.service.impl;

import com.project.mapper.SalesMapper;
import com.project.mapper.BrowseLogMapper;
import com.project.pojo.BrowseLog;
import com.project.service.BrowseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class BrowseLogServiceImpl implements BrowseLogService {
    @Autowired
    private BrowseLogMapper browseLogMapper;
    @Autowired
    private SalesMapper salesMapper;

    @Override
    public void addBrowseLog(BrowseLog browseLog) {
        browseLog.setTimeStamp(LocalDateTime.now());
        browseLogMapper.addBrowserLog(browseLog);
    }

    @Override
    public List<BrowseLog> getBrowseLogBySalesman(String userId) {
        Integer id = Integer.parseInt(userId);
        Integer cateId = salesMapper.getTypeById(id);
        return browseLogMapper.getLogByCateId(cateId);
    }
}
