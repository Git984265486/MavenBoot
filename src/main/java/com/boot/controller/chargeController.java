package com.boot.controller;

import com.boot.damain.chargeInfo;
import com.boot.damain.tUser;
import com.boot.damain.totalRow;
import com.boot.tools.chargeTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class chargeController {

    @Autowired
    private com.boot.service.chargeService chargeService;

    /**【列出收费信息，含按条件查找】**/
    @RequestMapping("/listChargeInfo")
    @ResponseBody
    public Map<String , Object> ListChargeInfo(@RequestParam(value = "page",defaultValue = "1") int page,
                             @RequestParam(value = "limit",defaultValue = "10")int size,
                             @RequestParam(value = "searchParams",defaultValue = "") String searchParams) throws ParseException{
        HashMap<String , Object> map = new HashMap<>();
        PageHelper.startPage(page,size);
        List<chargeInfo> listCharge = new ArrayList<>();

        if (searchParams == null || searchParams.equals("")){
            listCharge = chargeService.selectChargeList();
        }else{
            chargeTools tool = new chargeTools(searchParams);
            listCharge = chargeService.filterChargeList(tool.startTimeDate,tool.endTimeDate,tool.billno ,tool.carno,
                    tool.cartype ,tool.cz,tool.jcxm,tool.memo);
        }
        PageInfo<chargeInfo>pageInfo = new PageInfo<>(listCharge);
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**【查询一段时间内的数据】**/
    @RequestMapping("/selectDataByTime")
    @ResponseBody
    public Map<String,Object> selectDataByTime(@RequestParam(value = "page",defaultValue = "1") int page,
                                               @RequestParam(value = "limit",defaultValue = "10")int size,
                                               @RequestParam(value = "startTime",defaultValue = "")String startTime,
                                               @RequestParam(value = "endTime",defaultValue = "")String endTime){
        HashMap<String , Object> map = new HashMap<>();
        //System.out.println("开始时间：" + startTime + "\t结束时间:" + endTime);
        PageHelper.startPage(page,size);
        if (!startTime.equals("") && !endTime.equals("")){
            startTime = startTime + " 00:00:00";
            endTime = endTime + " 23:59:59";
        }
        List<chargeInfo> listData = chargeService.selectDataByTime(startTime,endTime);
        PageInfo<chargeInfo>pageInfo = new PageInfo<>(listData);
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }


    /**【返回当天自增编号】**/
    @RequestMapping("/seleLastCharge")
    @ResponseBody
    public Map<String , Object> seleLastCharge() throws ParseException {
        HashMap<String , Object> map = new HashMap<>();
        chargeInfo lastData = chargeService.seleLastCharge();   //最近插入的数据，billno != tmpLR
        chargeTools tools = new chargeTools("");
        String billNum = tools.getBillNo(lastData.getBillno());
        //System.out.println("获得当天自增编号为:\t" + billNum);
        map.put("data",billNum);
        return map;
    }

    /**【收费登记】**/
    @RequestMapping("/addChargeInfo")
    @ResponseBody
    public Map<String , Object> addChargeInfo(@RequestParam String addData) throws ParseException {
        HashMap<String , Object> map = new HashMap<>();

        chargeTools tools = new chargeTools("");
        tools.getSystemTime();
        chargeInfo chargeData = tools.setDataToObj(addData);
        if (chargeData != null){
            chargeService.addChargeInfo(chargeData);
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        return map;
    }

    /**【删除一段时间内的数据】**/
    @RequestMapping("/deleteDataByTime")
    @ResponseBody
    public Map<String ,Object> deleteDataByTime(@RequestParam String DelSTime,
                                                @RequestParam String DelETime){
        HashMap<String , Object> map = new HashMap<>();
        //System.out.println("传过来的日期："+ DelSTime + "\tDelETime:" + DelETime);
        String result = "";
        if (DelSTime != null && !DelSTime.equals("") && DelETime != null && !DelETime.equals("")){
            DelSTime = DelSTime + " 00:00:00";
            DelETime = DelETime + " 23:59:59";
            //System.out.println("拼接后的时间："+ DelSTime + "\tDelETime:" + DelETime);
            chargeService.deleteDataByTime(DelSTime,DelETime);
            result = "DelSuccess";
        }
        map.put("result",result);
        return map;
    }
}
