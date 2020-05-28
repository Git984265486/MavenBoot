package com.boot.service.impl;

import com.boot.damain.base;
import com.boot.mapper.baseMapper;
import com.boot.service.baseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class baseImpl implements baseService {

    @Autowired
    private baseMapper baseMapper;

    @Override
    public List<base> selectBaseList() {
        return baseMapper.selectBaseList();
    }
}
