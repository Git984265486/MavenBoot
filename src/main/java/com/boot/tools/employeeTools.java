package com.boot.tools;

import com.boot.damain.employee;
import net.sf.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class employeeTools {

    /**【获得以jh开头的自动递增字符串】**/
    public String getCodeNum(String lastCode){
        String returnCode = null;
        System.out.println("传过来的code:"+ lastCode);
        if (lastCode != null){
            String s = "abcdefghijklmnopqrstuvwxyz";
            for (int j = 0; j < 26; j++){
                for (int i = 0; i < 26; i++){

                    returnCode = "jh" + s.charAt(j) + s.charAt(i);

                    if (lastCode.equals(returnCode)){
                        if ( i < 25 ){
                            returnCode = "jh" + s.charAt(j) + s.charAt(i+1);
                        }else{
                            returnCode = "jh" + s.charAt(j+1) + s.charAt(0);
                        }
                        return returnCode;
                    }
                    //System.out.print( "\t" + returnCode);
                }
                //System.out.println();
            }
            return returnCode;
        }else {
            return returnCode;
        }
    }

    /**【是否停用数字转字符串】**/
    public String getStopString(int stop){
        if (stop == 0){
            return "否";
        }else {
            return "是";
        }
    }

    /**【全部是否停用数字转字符串】**/
    public List<employee> listEmployee(List<employee> list){
        List<employee> listData = null;
        if (list != null){
            for (int i = 0 ; i < list.size() ; i++){
                list.get(i).setIsStop(getStopString(list.get(i).getStop()));
            }
            listData = list;
        }
        return listData;
    }

    /**将传过来的json数据赋值到employee中**/
    public employee initData(String requestData) throws ParseException {
        employee user = null;
        if (requestData != null){
            user = new employee();
            JSONObject jsonobject = JSONObject.fromObject(requestData);
            System.out.println("传过来的requestData：" + jsonobject.toString());
            user.setName(jsonobject.getString("name"));
            user.setPy(jsonobject.getString("py"));
            user.setBirthday(StrToDate(jsonobject.getString("birthday")));
            user.setCardno(jsonobject.getString("cardno"));
            user.setAddress(jsonobject.getString("address"));
            user.setSex(jsonobject.getString("sex"));
            user.setDeptid(Integer.parseInt(jsonobject.getString("deptid")));
            user.setPhone(jsonobject.getString("phone"));
            user.setMobile(jsonobject.getString("mobile"));
            user.setMemo(jsonobject.getString("memo"));
        }
        return user;
    }

    /**String转Date**/
    public Date StrToDate(String strTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        if (strTime != null && !strTime.equals("")){
            time = format.parse(strTime);
        }
        return time;
    }

}
