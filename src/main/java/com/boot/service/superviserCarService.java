package com.boot.service;

import com.boot.damain.superviserCar;

import java.util.List;

public interface superviserCarService {

    /**【获取30天内的数据】**/
    public List<superviserCar> seleListData();

    /**【获取所有的数据】**/
    public List<superviserCar> selectAll(String startTime, String endTime);

    /**【删除指定时间段内的数据】**/
    public void delDataByTime(String DelSTime,String DelETime);

    /**【通过车牌号码模糊查询】**/
    public List<superviserCar> selectDataByCarNo(String carNo);

    /**【通过车牌号码精确查询】**/
    public List<superviserCar> selectByCarNo(String carNo);

    /**【通过keyID精准查询】**/
    public superviserCar selectDetailBykeyID(String keyID);

    /**【通过in_check字段获取数据】**/
    public List<superviserCar> selectByInCheck(String inCheck ,String carNo);

    /**【通过keyID更新车辆检查状态】**/
    public void updateCarStatus(superviserCar car);

    /**【通过车牌号码更新车辆检查状态】**/
    public void updateCarByCarNo(superviserCar car);

    /**【查询当天录入两小时未完成检测车辆车牌】**/
    public List<superviserCar> selectTwoHour();
}
