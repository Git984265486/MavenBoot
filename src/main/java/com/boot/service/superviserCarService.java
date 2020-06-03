package com.boot.service;

import com.boot.damain.superviserCar;

import java.util.List;

public interface superviserCarService {

    /**【获取30天内的数据】**/
    public List<superviserCar> seleListData();

    /**【通过车牌号码模糊查询】**/
    public List<superviserCar> selectDataByCarNo(String carNo);

    /**【通过keyID精准查询】**/
    public superviserCar selectDetailBykeyID(String keyID);

    /**【通过in_check字段获取数据】**/
    public List<superviserCar> selectByInCheck(String inCheck ,String carNo);

    /**【通过keyID更新车辆检查状态】**/
    public void updateCarStatus(superviserCar car);
}
