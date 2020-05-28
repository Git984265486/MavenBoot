package com.boot.controller;

import com.boot.damain.base;
import com.boot.service.baseService;
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
public class baseController {

    @Autowired
    private baseService baseService;


    @RequestMapping("/listBaseData")
    @ResponseBody
    public Map<String , Object> listBaseData(@RequestParam(value = "page",defaultValue = "1") int page,
                                             @RequestParam(value = "limit",defaultValue = "10")int size){
        HashMap<String , Object> map = new HashMap<>();
        PageHelper.startPage(page,size);
        //if (!code.equals("") && code != null){
        //System.out.println("--->【code：" + code + "\tpage:" + page +"】<---");
        //}
        List<base> baseList = baseService.selectBaseList();
        PageInfo<base> pageInfo = new PageInfo<>(baseList);
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }



}
