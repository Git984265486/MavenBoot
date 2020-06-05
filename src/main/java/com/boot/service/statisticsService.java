package com.boot.service;

import com.boot.damain.statistics;

import java.util.List;


public interface statisticsService {

    /**【统计收费信息】**/
    public List<statistics> statisticsData(String years);

}
