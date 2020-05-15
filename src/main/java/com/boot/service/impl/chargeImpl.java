package com.boot.service.impl;

import com.boot.damain.chargeInfo;
import com.boot.mapper.chargeMapper;
import com.boot.service.chargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class chargeImpl implements chargeService {

    @Autowired
    private chargeMapper chargeMapper;

    @Override
    public List<chargeInfo> selectChargeList() {
        return chargeMapper.selectChargeList();
    }


    @Override
    public List<chargeInfo> filterChargeList(Date startTime, Date endTime, String billno , String carno
            , String cartype , String cz, String jcxm, String memo) {
        return chargeMapper.filterChargeList(startTime,endTime,billno ,carno
                ,cartype ,cz,jcxm,memo);
    }
}
