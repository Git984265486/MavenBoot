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

    /**【查询全部数据】**/
    @Override
    public List<base> selectBaseList() {
        return baseMapper.selectBaseList();
    }

    /**【查询最后操作的一条数据】**/
    @Override
    public base lastBaseData() {
        return baseMapper.lastBaseData();
    }

    /**【通过code查询数据】**/
    @Override
    public base selBaseByCode(String code) {
        return baseMapper.selBaseByCode(code);
    }

    /**【通过code删除数据】**/
    @Override
    public void delBaseByCode(String code) {
        baseMapper.delBaseByCode(code);
    }

    /**【通过code更新数据】**/
    @Override
    public void updateBaseByCode(base base) {
        baseMapper.updateBaseByCode(base.getName(),base.getPy(),base.getStop(),base.getMemo(),base.getSpdl(),base.getSfbz(),base.getCode());
    }

    /**【插入数据】**/
    @Override
    public void insertBaseData(base base) {
        baseMapper.insertBaseData(base.getCode(),base.getName(),base.getPy(),base.getStop(),base.getMemo(),base.getSpdl(),base.getSfbz());
    }
}
