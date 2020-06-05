package com.boot.mapper;

import com.boot.damain.statistics;

import java.util.List;

public interface statisticsMapper {

    /**【统计收费信息】**/
    public List<statistics> statisticsData(String years);

}
