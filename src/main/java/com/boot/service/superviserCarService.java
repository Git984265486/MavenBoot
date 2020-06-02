package com.boot.service;

import com.boot.damain.superviserCar;

import java.util.List;

public interface superviserCarService {

    /**【获取30天内的数据】**/
    public List<superviserCar> seleListData();

    /**【通过车牌号码模糊查询】**/
    public List<superviserCar> selectDataByCarNo(String carNo);

    /**【通过车牌号码精准查询】**/
    public superviserCar selectDetailByCarNo(String carno);

    /**【通过车牌号码更新车辆检查状态】**/
    public void updateCarStatus(superviserCar car);
}
