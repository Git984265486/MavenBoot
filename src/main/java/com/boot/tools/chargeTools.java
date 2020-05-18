package com.boot.tools;

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


}
