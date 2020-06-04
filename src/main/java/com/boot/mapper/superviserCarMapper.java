package com.boot.mapper;

import com.boot.damain.superviserCar;

import java.util.List;

public interface superviserCarMapper {

    /**【获取30天内的数据】**/
    public List<superviserCar> seleListData();

    /**【通过车牌号码模糊查询】**/
    public List<superviserCar> selectDataByCarNo(String carNo);

    /**【通过车牌号码精确查询】**/
    public List<superviserCar> selectByCarNo(String carNo);

    /**【通过keyID精准查询】**/
    public superviserCar selectDetailBykeyID(String keyID);

    /**【通过in_check字段获取数据】**/
    public List<superviserCar> selectByInCheck(String inCheck,String carNo);

    /**【通过keyID更新车辆检查状态】**/
    public void updateCarStatus(String check ,String wjStart ,String wjEnd ,String ajStart ,String ajEnd , String hjStart ,String hjEnd , String startTime ,
                                String endTime ,String wjUsetime , String ajUsetime ,String hjUsetime ,String usetime ,String result ,String keyID);

    /**【通过车牌号码更新车辆状态】**/
    public void updateCarByCarNo(String check ,String wjStart ,String wjEnd ,String ajStart ,String ajEnd , String hjStart ,String hjEnd , String startTime ,
                                 String endTime ,String wjUsetime , String ajUsetime ,String hjUsetime ,String usetime ,String result ,String carno);
}
