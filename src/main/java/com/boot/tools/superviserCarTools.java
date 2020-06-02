package com.boot.tools;

import com.boot.damain.superviserCar;

import java.text.ParseException;
import java.util.Date;

public class superviserCarTools {

    /**【计算更新车辆检测数据】**/
    public superviserCar updateCarData(superviserCar carInfo) throws ParseException {
        superviserCar updateCar = new superviserCar();
        if (carInfo != null){
            updateCar.setKeyID(carInfo.getKeyID());
            updateCar.setCarno(carInfo.getCarno());
            updateCar.setIn_check(carInfo.getIn_check());
            updateCar.setWj_start(carInfo.getWj_start());
            updateCar.setWj_end(carInfo.getWj_end());
            updateCar.setAj_start(carInfo.getAj_start());
            updateCar.setAj_end(carInfo.getAj_end());
            updateCar.setHj_start(carInfo.getHj_start());
            updateCar.setHj_end(carInfo.getHj_end());
            updateCar.setStart_time(carInfo.getStart_time());
            updateCar.setEnd_time(carInfo.getEnd_time());
            updateCar.setWj_usetime(getUseTime(carInfo.getWj_start(),carInfo.getWj_end()));
            updateCar.setAj_usetime(getUseTime(carInfo.getAj_start(),carInfo.getAj_end()));
            updateCar.setHj_usetime(getUseTime(carInfo.getHj_start(),carInfo.getHj_end()));
            updateCar.setUsetime(getUseTime(carInfo.getStart_time(),carInfo.getEnd_time()));
            updateCar.setOperator(carInfo.getOperator());
            updateCar.setResult(carInfo.getResult());
        }
        return updateCar;
    }

    /**【计算两个时间段相减】**/
    public String getUseTime(String startTime , String endTime) throws ParseException {
        String useTime = null;
        if (startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")){
            timeTools time = new timeTools();
            Date start = time.StrToDate(startTime);
            Date end = time.StrToDate(endTime);

            long use = end.getTime() - start.getTime() ;    //得到毫秒级
            System.out.println("【计算时间差得出的毫秒值】" + use);

            long minutes = use / (1000 * 60);               //转换成分钟
            System.out.println("【时间转换得到的分钟值】" + minutes);

            if (minutes >= 60){                             //大于等于60分钟
                int hours = (int)Math.floor(minutes/60);
                int minute = (int)minutes % 60;
                useTime = hours + "小时" + minute + "分";
            }else {
                useTime =  minutes + "分";
            }

            System.out.println("【最终时间转换得到的值】" + useTime);
        }
        return useTime;
    }

}
