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

            String wjUseTime = getUseTime(carInfo.getWj_start(),carInfo.getWj_end());
            updateCar.setWj_usetime(wjUseTime);

            String ajUseTime = getUseTime(carInfo.getAj_start(),carInfo.getAj_end());
            updateCar.setAj_usetime(ajUseTime);

            String hjUseTime = getUseTime(carInfo.getHj_start(),carInfo.getHj_end());
            updateCar.setHj_usetime(hjUseTime);

            updateCar.setOperator(carInfo.getOperator());
            updateCar.setResult(carInfo.getResult());
            System.out.println("【拿到外检耗时】" + wjUseTime);
            System.out.println("【拿到安检耗时】" + ajUseTime);
            System.out.println("【拿到环检耗时】" + hjUseTime);
            //System.out.println("【拿到总耗时】" + useTime);

            /**【判断检验项是否完成】**/
            if (wjUseTime != null &&  ajUseTime != null && hjUseTime != null){
                timeTools time = new timeTools();
                updateCar.setEnd_time(time.getNowTime());
                updateCar.setResult("1");
                String allUseTime = getUseTime(carInfo.getStart_time(),updateCar.getEnd_time());
                updateCar.setUsetime(allUseTime);
                System.out.println("【---拿到总耗时---】" + allUseTime);
            }else {
                updateCar.setUsetime(carInfo.getUsetime());
            }
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

            int hours = (int)Math.floor(minutes/60);        //转换成小时
            System.out.println("【时间转换得到的小时值】" + hours);

            if (minutes > 60){                      //耗时大于60分钟时
                int min = (int)minutes % 60 ;                       //分钟（取余）
                if (hours > 24){                    //耗时大于24小时
                    int day = (int)Math.floor(hours/24);            //天数（取整）
                    int hour = (int)hours % 24;                       //小时（取余）
                    useTime = day + "天"+ hour + "小时" + min + "分";
                }else{
                    useTime = hours + "小时" + min + "分";
                }
            }else {
                useTime =  minutes + "分";
            }
            System.out.println("【最终时间转换得到的值】" + useTime);
        }
        return useTime;
    }



}
