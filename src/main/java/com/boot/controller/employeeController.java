package com.boot.controller;

import com.boot.damain.employee;
import com.boot.service.employeeService;
import com.boot.tools.employeeTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class employeeController {

    @Autowired
    private employeeService service;

    /**【列出tEmployee表的数据】**/
    @RequestMapping("/employeeList")
    @ResponseBody
    public Map<String ,Object> employeeList(@RequestParam(value = "page",defaultValue = "1") int page,
                                            @RequestParam(value = "limit",defaultValue = "15")int size){
        HashMap<String , Object> map = new HashMap<>();
        employeeTools tools = new employeeTools();
        PageHelper.startPage(page,size);
        List<employee> list = service.selectListEmployee();
        PageInfo<employee> listData = new PageInfo<>(tools.listEmployee(list));
        map.put("code","0");
        map.put("count",listData.getTotal());
        map.put("data",listData.getList());
        return map;
    }

    /**【获取最后一条数据】**/
    @RequestMapping("/getLastData")
    @ResponseBody
    public employee getLastData(){
        employee employee = service.selectLastData();
        if (employee!=null){
            employeeTools tools = new employeeTools();
            String code = tools.getCodeNum(employee.getCode());
            //System.out.println("要插入的code值：" + code);
            employee.setIsStop(tools.getStopString(employee.getStop()));
        }
        return employee;
    }

    /**【添加员工，同时添加登录用户信息】**/
    @RequestMapping("/addEmployee")
    @ResponseBody
    public Map<String ,Object> addEmployee(@RequestParam String requestData) throws ParseException {
        HashMap<String , Object> map = new HashMap<>();
        String result = "";
        employeeTools tools = new employeeTools();
        employee userInfo = tools.initData(requestData);
        employee lastEmp = service.selectLastData();    //获取最后插入数据库的员工，获取他的Code来生成新员工的Code
        String code = tools.getCodeNum(lastEmp.getCode());
        //System.out.println("要插入的code值：" + code);
        if (userInfo != null){
            userInfo.setCode(code);
            service.addEmployee(userInfo);
            result = "success";
        }else{
            result = "fail";
        }
        map.put("result",result);
        map.put("code",0);
        return map;
    }

    /**【删除员工信息】**/
    @RequestMapping("/delEmployee")
    @ResponseBody
    public Map<String , Object> delEmployee(@RequestParam String code){
        HashMap<String , Object> map = new HashMap<>();
        String result = "";
        //System.out.println("传过来的Code值：" + code);
        if (code != null && !code.equals("")){
            service.delEmployee(code);      //删除员工信息
            result = "success";
        }
        map.put("result",result);
        return map;
    }

    /**【更新员工信息，更新字段:stop】**/
    @RequestMapping("/stopEmployee")
    @ResponseBody
    public Map<String , Object> stopEmployee(@RequestParam String code ,  @RequestParam String stop){
        HashMap<String,Object> map = new HashMap<>();
        String result = "";
        //System.out.println("前端传过来的数据:\tcode:" + code + "\tstop:" + stop);
        if (code != null && !code.equals("") && stop != null && !stop.equals("")){
            service.stopEmployee(code,Integer.parseInt(stop));
            result = "success";
        }
        map.put("result",result);
        return map;
    }

    /**【通过code拿到员工信息】**/
    @RequestMapping("/selectEmplByCode")
    @ResponseBody
    public Map<String , Object> selectEmplByCode(@RequestParam String code) throws ParseException {
        HashMap<String,Object> map = new HashMap<>();
        //System.out.println("前端传过来获取员工信息数据code:\t" + code);
        if (code != null && !code.equals("")){
            employee employee = service.selectEmployeeByCode(code);
            employeeTools tools = new employeeTools();
            employee.setBirthday(tools.timeFormat(employee.getBirthday()));
            map.put("employee",employee);
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        return map;
    }

    /**【通过code修改员工信息】**/
    @RequestMapping("/editEmployeeByCode")
    @ResponseBody
    public Map<String , Object> editEmployeeByCode(@RequestParam String edtiData) throws ParseException {
        HashMap<String,Object> map = new HashMap<>();
        //System.out.println("前端传过来员工信息数据edtiData:\t" + edtiData);
        if (edtiData != null && !edtiData.equals("")){
            employeeTools tool = new employeeTools();
            employee em = tool.initDataUpdate(edtiData);
            service.editEmployee(em);
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        return map;
    }
}
