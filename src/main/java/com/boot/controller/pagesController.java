package com.boot.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class pagesController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**【员工信息页面】**/
    @RequestMapping("/userPage")
    public String userPage(){
        return "pages/userInfo";
    }

    /**【收费记录页面】**/
    @RequestMapping("/chargePage")
    public String chargePage(){
        return "pages/chargeInfo";
    }

    /**【员工添加页面】**/
    @RequestMapping("/addEmployeePage")
    public String addEmployee(){
        return "pages/addEmployee";
    }

    /**【登录页面】**/
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    /**【员工信息项编辑页面】**/
    @RequestMapping("/editEmployeePage")
    public String editEmployeePage(){
        return "pages/editEmployee";
    }

    /**【收费登记页面】**/
    @RequestMapping("/registerCharge")
    public String registerCharge(){
        return "pages/registerCharge";
    }

    /**【收费项编辑页面】**/
    @RequestMapping("/editBasePage")
    public String editBasePage(){
        return "pages/editBase";
    }

    /**【收费项添加页面】**/
    @RequestMapping("/addBasePage")
    public String addBasePage(){
        return "pages/addBase";
    }
}
