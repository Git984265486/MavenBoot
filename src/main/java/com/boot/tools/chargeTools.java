package com.boot.tools;

import com.boot.damain.chargeInfo;
import net.sf.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class chargeTools {

    public String startTime = null;    //开始时间
    public String endTime = null;      //结束时间
    public String billno = null;       //编号
    public String carno = null;        //车牌
    public String cartype = null;      //车辆类型
    public String cz = null;           //支付方式
    public String jcxm = null;         //检测项目
    public String memo = null;         //备注
    public Date startTimeDate = null;          //转换后的开始时间
    public Date endTimeDate = null;            //转换后的结束时间
    public chargeTools(String searchParams) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (searchParams != null && !searchParams.equals("")){
            JSONObject jsonobject = JSONObject.fromObject(searchParams);
            startTime = jsonobject.getString("startTime");
            endTime = jsonobject.getString("endTime");
            billno = jsonobject.getString("billno");
            carno = jsonobject.getString("carno");
            cartype = jsonobject.getString("cartype");
            cz = jsonobject.getString("cz");
            jcxm = jsonobject.getString("jcxm");
            memo = jsonobject.getString("memo");
            if (startTime.length() != 0){
                startTimeDate = format.parse(startTime);
            }else{
                startTimeDate = null;
            }

            if (endTime.length() != 0){
                endTimeDate = format.parse(endTime);
            }else {
                endTimeDate = null;
            }
            if (billno.length() == 0){
                billno = null;
            }
            if (carno.length() == 0){
                carno = null;
            }
            if (cartype.length() == 0){
                cartype = null;
            }

            if (cz.length() == 0){
                cz = null;
            }

            if (jcxm.length() == 0){
                jcxm = null;
            }
            if (memo.length() == 0){
                memo = null;
            }
            System.out.print("startTimeDate:" + startTimeDate);
            System.out.print("\tendTimeDate:" + endTimeDate);
            System.out.print("\tbillno:" + billno);
            System.out.print("\tcarno:" + carno);
            System.out.println("cartype:" + cartype);
            System.out.print("\tflag:" + cz);
            System.out.print("\tjcxm:" + jcxm);
            System.out.print("\tmemo:" + memo);
        }

    }

    /**【当天自增编号  billno  】**/
    public String getBillNo(String billno){
        String billNum = "";
        if (!billno.equals("") && billno != null){
            String bills [] = billno.split("-");
            if (bills.length != 0){
                for (int i = 0 ; i < bills.length ; i ++ ) {
                    System.out.println("切割的字符串:\t" + bills[i]);
                }
                if (bills[0].equals(timeNumber())){     //判断最后处理的数据的日期是否是当天
                    billNum = timeNumber() + "-" +getNumber(bills[1]);
                }else{                                  //不是当天则编号从0001重新开始
                    billNum = timeNumber() + "-0001";
                }
            }
        }
        return billNum;
    }

    /**【获得四位数的数字字符串】**/
    public String getNumber(String number){
        String numStr = "";
        int num = 0;
        if (number != null && !number.equals("")){
            num = 1 + Integer.parseInt(number);
            if (num <= 9){
                numStr = "000" + num;
            }else if (num <= 99){
                numStr = "00" + num;
            }else{
                numStr = "0" + num;
            }
        }
        System.out.println("【获得四位数的数字字符串】" + numStr);
        return numStr;
    }

    /**【指定时间格式】**/
    public String timeNumber(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String timeData = dateFormat.format(new Date());
        System.out.println("获取指定的时间格式【yyyyMMdd】数据:\t" + timeData);
        return timeData;
    }

    /**【将json串数据赋值到对象中】**/
    public chargeInfo setDataToObj(String addData) throws ParseException {
        chargeInfo info = new chargeInfo();
        if (addData != null && !addData.equals("")){
            JSONObject jsonobject = JSONObject.fromObject(addData);
            System.out.println("传过来的requestData：" + jsonobject.toString());
            //System.out.println("传过来的billNum：" + jsonobject.getString("billNum"));
            String dj = jsonobject.getString("dj"); //单价
            String count = jsonobject.getString("count");//总数
            String billNum = jsonobject.getString("billNum");

            info.setBillno(billNum);                /////报错
            info.setCarno(jsonobject.getString("carNum"));
            info.setDtdate(StrToDate(jsonobject.getString("time")));
            info.setCartype(jsonobject.getString("carType"));
            info.setCz(jsonobject.getString("payType"));
            info.setPcode(jsonobject.getString("carVin"));
            info.setJcxm(jsonobject.getString("checkProject"));
            info.setZsl(Integer.parseInt(count));
            info.setZje(TotalNumber(count,dj));
            info.setOptname(jsonobject.getString("opterater"));
            info.setMemo(jsonobject.getString("remark"));
        }
        return info;
    }

    /**【字符串转数字】**/
    public int StrToNnmber(String str){
        int number = 0;
        if (str != null && !str.equals("")){
            number =Integer.parseInt(str);
        }
        return number;
    }

    /**【String转Double】**/
    public Double strToDouble(String strDouble){
        Double number = 0.0;
        if (strDouble != null && !strDouble.equals("")){
            number = new Double(strDouble);
        }
        return number;
    }

    /**【计算总价格】**/
    public Double TotalNumber(String count , String dj){   //总数量 × 单价
        Double total = 0.0;
        if (count != null && !count.equals("") && dj != null && !dj.equals("")){
            total = Integer.parseInt(count) * strToDouble(dj);
        }
        System.out.println("【总价】" + total);
        return total;
    }

    /**【String转Date】**/
    public Date StrToDate(String strTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        if (strTime != null && !strTime.equals("")){
            time = format.parse(strTime);
        }
        return time;
    }

    /**【拿到当前系统时间】**/
    public Date getSystemTime() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String times = format.format(time);
        System.out.println("【拿到当前系统时间】" + times);
        return time;
    }

}
