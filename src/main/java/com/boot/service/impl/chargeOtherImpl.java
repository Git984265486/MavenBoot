package com.boot.service.impl;

import com.boot.damain.chargeOther;
import com.boot.mapper.chargeOtherMapper;
import com.boot.service.chargeOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class chargeOtherImpl implements chargeOtherService {

    @Autowired
    private chargeOtherMapper otherMapper;


    /**【拿到所有的数据】**/
    @Override
    public List<chargeOther> selectAllData() {
        return otherMapper.selectAllData();
    }

    /**【删除前countNum条数据】**/
    @Override
    public void delCountData(int countNum) {
        otherMapper.delCountData(countNum);
    }
}
