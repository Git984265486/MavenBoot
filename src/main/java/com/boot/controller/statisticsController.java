package com.boot.controller;

import com.boot.damain.statistics;
import com.boot.service.statisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class statisticsController {

    @Autowired
    private statisticsService service;


    @RequestMapping("/statisticsData")
    @ResponseBody
    public Map<String ,Object> statisticsData(@RequestParam(value = "years",defaultValue = "")String years){
        HashMap<String ,Object> map = new HashMap<>();
        String year = "";
        if (!years.equals("")){         //没传入年份
            year = years;
        }else{                          //默认获取当前年份数据
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR) + "";
        }
        System.out.println("年份:" + year);
        List<statistics> listData = service.statisticsData(year);
        map.put("result","success");
        map.put("listData",listData);
        return map;
    }

}
