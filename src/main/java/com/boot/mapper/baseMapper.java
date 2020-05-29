package com.boot.mapper;

import com.boot.damain.base;

import java.util.List;

public interface baseMapper {

    /**【查询全部数据】**/
    public List<base> selectBaseList();

    /**【查询最后操作的一条数据】**/
    public base lastBaseData();

    /**【通过code查询数据】**/
    public base selBaseByCode(String code);

    /**【通过code删除数据】**/
    public void delBaseByCode(String code);

    /**【通过code更新数据】**/
    public void updateBaseByCode(String name ,String py ,String stop, String memo ,String spdl, String sfbz ,String code);

    /**【插入数据】**/
    public void insertBaseData(String code , String name , String py , String stop , String memo , String spdl ,String sfbz);
}
