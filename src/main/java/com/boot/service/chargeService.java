package com.boot.service;

import com.boot.damain.chargeInfo;

import java.util.Date;
import java.util.List;

public interface chargeService {

    List<chargeInfo> selectChargeList();

    List<chargeInfo> filterChargeList(Date startTime,Date endTime,String billno ,String carno
            ,String cartype , String cz,String jcxm,String memo);

}
