package com.boot.mapper;

import com.boot.damain.chargeInfo;

import java.util.Date;
import java.util.List;

public interface chargeMapper {

    /**【列出30天内的数据】**/
    List<chargeInfo> selectChargeList();

    /**【按条件查询数据】**/
    List<chargeInfo> filterChargeList(Date startTime, Date endTime, String billno , String carno
            , String cartype , String cz, String jcxm, String memo);

}
