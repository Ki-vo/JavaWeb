package com.project.service;

import com.project.pojo.BrowseLog;

import java.util.List;

public interface BrowseLogService {
    void addBrowseLog(BrowseLog browseLog);

    List<BrowseLog> getBrowseLogBySalesman(String userId);
}
