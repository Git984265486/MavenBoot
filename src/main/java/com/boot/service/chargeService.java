package com.boot.service;

import com.boot.damain.chargeInfo;

import java.util.Date;
import java.util.List;

public interface chargeService {

    /**【列出30天内的数据】**/
    List<chargeInfo> selectChargeList();

    /**【按条件查询数据】**/
    List<chargeInfo> filterChargeList(Date startTime,Date endTime,String billno ,String carno
            ,String cartype , String cz,String jcxm,String memo);

    /**【获取最近操作的一条数据】**/
    public chargeInfo seleLastCharge();

    /**【录入收费信息】**/
    public void addChargeInfo(chargeInfo charge);
}
