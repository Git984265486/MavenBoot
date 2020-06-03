package com.boot.service.impl;

import com.boot.damain.superviserCar;
import com.boot.mapper.superviserCarMapper;
import com.boot.service.superviserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperviserCarImpl implements superviserCarService {

    @Autowired
    private superviserCarMapper superviserMapper;

    /**【获取30天内的数据】**/
    @Override
    public List<superviserCar> seleListData() {
        return superviserMapper.seleListData();
    }

    /**【通过车牌号码模糊查询】**/
    @Override
    public List<superviserCar> selectDataByCarNo(String carNo) {
        return superviserMapper.selectDataByCarNo(carNo);
    }

    /**【通过keyID精准查询】**/
    @Override
    public superviserCar selectDetailBykeyID(String keyID) {
        return superviserMapper.selectDetailBykeyID(keyID);
    }

    /**【通过in_check字段获取数据】**/
    @Override
    public List<superviserCar> selectByInCheck(String inCheck,String carNo) {
        return superviserMapper.selectByInCheck(inCheck,carNo);
    }

    /**【通过keyID更新车辆检查状态】**/
    @Override
    public void updateCarStatus(superviserCar car) {
        superviserMapper.updateCarStatus(car.getIn_check(),car.getWj_start(),car.getWj_end(),car.getAj_start(),car.getAj_end(),car.getHj_start(),
                car.getHj_end(),car.getStart_time(),car.getEnd_time(),car.getWj_usetime(),car.getAj_usetime(),car.getHj_usetime(),car.getUsetime(),
                car.getResult(),String.valueOf(car.getKeyID()));
    }
}
