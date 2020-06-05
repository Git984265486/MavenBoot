package com.boot.service.impl;

import com.boot.damain.statistics;
import com.boot.mapper.statisticsMapper;
import com.boot.service.statisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class statisticsImpl implements statisticsService {

    @Autowired
    private statisticsMapper mapper;

    /**【统计收费信息】**/
    @Override
    public List<statistics> statisticsData(String years) {
        return mapper.statisticsData(years);
    }
}
