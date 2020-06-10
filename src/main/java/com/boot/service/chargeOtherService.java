package com.boot.service;

import com.boot.damain.chargeOther;

import java.util.List;

public interface chargeOtherService {

    /**【拿到所有的数据】**/
    public List<chargeOther> selectAllData();

    /**【删除前countNum条数据】**/
    public void delCountData(int countNum);
}
