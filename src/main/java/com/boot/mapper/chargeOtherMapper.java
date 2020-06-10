package com.boot.mapper;

import com.boot.damain.chargeOther;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface chargeOtherMapper {

    /**【拿到所有的数据】**/
    public List<chargeOther> selectAllData();

    /**【删除前countNum条数据】**/
    public void delCountData(@Param("countNum") int countNum);
}
