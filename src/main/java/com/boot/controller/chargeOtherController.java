package com.boot.controller;


import com.boot.damain.chargeOther;
import com.boot.service.chargeOtherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class chargeOtherController {

    @Autowired
    private chargeOtherService service;

    /**【拿到所有的数据】**/
    @RequestMapping("/selectAllData")
    @ResponseBody
    public Map<String, Object> selectAllData(@RequestParam(value = "page",defaultValue = "1")int page,
                                             @RequestParam(value = "limit",defaultValue = "10")int size){
        HashMap<String,Object> map = new HashMap<>();
        PageHelper.startPage(page,size);
        List<chargeOther> listData = service.selectAllData();
        PageInfo<chargeOther> info = new PageInfo<>(listData);
        map.put("code",0);
        map.put("count",info.getTotal());
        map.put("data",info.getList());
        return map;
    }

    /**【删除前countNum条数据】**/
    @RequestMapping("/delCountData")
    @ResponseBody
    public Map<String,Object> delCountData(@RequestParam(value = "countNum",defaultValue = "0")int countNum){
        HashMap<String,Object> map = new HashMap<>();
        String result = "";
        if (countNum != 0){
            service.delCountData(countNum);
            result = "DelSuccess";
        }
        map.put("result",result);
        return map;
    }

}
