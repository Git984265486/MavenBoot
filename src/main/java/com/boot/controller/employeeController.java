package com.boot.controller;

import com.boot.damain.employee;
import com.boot.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class employeeController {

    @Autowired
    private employeeService service;

    @RequestMapping("/employeeList")
    @ResponseBody
    public Map<String ,Object> employeeList(){
        HashMap<String , Object> map = new HashMap<>();
        List<employee> list = service.selectListEmployee();
        map.put("code","0");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    @RequestMapping("/getLastData")
    @ResponseBody
    public employee getLastData(){
        employee employee = service.selectLastData();
        return employee;
    }


}
