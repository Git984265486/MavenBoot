package com.boot.service;

import com.boot.damain.base;

import java.util.List;

public interface baseService {

    /**【查询全部数据】**/
    public List<base> selectBaseList();

    /**【查询最后操作的一条数据】**/
    public base lastBaseData();

    /**【通过code查询数据】**/
    public base selBaseByCode(String code);

    /**【通过code删除数据】**/
    public void delBaseByCode(String code);

    /**【通过code更新数据】**/
    public void updateBaseByCode(base base);

    /**【插入数据】**/
    public void insertBaseData(base base);
}
