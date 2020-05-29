package com.boot.tools;

import com.boot.damain.base;
import net.sf.json.JSONObject;

import java.util.List;

public class baseTools {

    /**【是否停用数字转字符串】**/
    public String getStopString(int stop){
        if (stop == 0){
            return "否";
        }else {
            return "是";
        }
    }

    public String getStopInt(String stop){
        if (stop.equals("是")){
            return "1";
        }else{
            return "0";
        }
    }

    /**【全部是否停用数字转字符串】**/
    public List<base> listBase(List<base> data){
        List<base> listData = null;
        if (data != null){
            for (int i = 0 ; i < data.size() ; i ++){
                data.get(i).setStop(getStopString(Integer.parseInt(data.get(i).getStop())));
            }
            listData = data;
        }
        return listData;
    }

    /**【自增编号】**/
    public String baseCode(String code){
        String codeStr = "0";
        if (code != null && !code.equals("")){
            int codeNum = 1 + Integer.parseInt(code);
            if (codeNum <= 99){
                codeStr = "0" + codeNum;
            }else{
                codeStr = codeStr + "";
            }
        }
        return codeStr;
    }

    /**【json数据赋值到对象中,编辑数据】**/
    public base baseJsonData(String jsonData){
        base base = new base();
        if(jsonData != null && !jsonData.equals("")){
            JSONObject jsonobject = JSONObject.fromObject(jsonData);
            base.setName(jsonobject.getString("name"));
            base.setCode(jsonobject.getString("code"));
            base.setPy(jsonobject.getString("py"));
            base.setMemo(jsonobject.getString("remark"));
            base.setStop(getStopInt(jsonobject.getString("stop")));
            base.setSpdl(jsonobject.getString("carType"));
            base.setSfbz(jsonobject.getString("charge"));
        }
        return base;
    }

    /**【json数据赋值到对象中,插入数据】**/
    public base insertJsonData(String insertData){
        base base = new base();
        if (insertData != null && !insertData.equals("")){
            JSONObject object = JSONObject.fromObject(insertData);
            base.setName(object.getString("name"));
            base.setPy(object.getString("py"));
            base.setMemo(object.getString("remark"));
            base.setStop(object.getString("stop"));
            base.setSpdl(object.getString("carType"));
            base.setSfbz(object.getString("charge"));
        }
        return base;
    }

}
