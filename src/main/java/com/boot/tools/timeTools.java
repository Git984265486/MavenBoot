package com.boot.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeTools {


    /**【获得当前系统时间】**/
    public String getNowTime(){
        String timeStr = null;
        //创建SimpleDateFormat对象，指定样式    2019-05-13 22:39:30
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        timeStr = format.format(time);
        //System.out.println("【时间工具:获取当前系统时间】" + timeStr);
        return timeStr;
    }

    /**【字符串转换成时间】**/
    public Date StrToDate(String timeStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        if (timeStr != null && !timeStr.equals("")){
            time = format.parse(timeStr);
        }
        //System.out.println("【时间工具:字符串转换成时间】" + time);
        return time;
    }

    /**【时间转换成字符串】**/
    public String DateToStr(Date date){
        String timeStr = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date != null && !date.equals("")){
            timeStr = format.format(date);
        }
        //System.out.println("【时间工具:时间转换成字符串】" + timeStr);
        return timeStr;
    }

    /**【时间转换成long】**/
    public long DateToLong(Date date){
        long timeLong = 0;
        if (date != null && !date.equals("")){
            timeLong = date.getTime();
        }
        return timeLong;
    }

}
