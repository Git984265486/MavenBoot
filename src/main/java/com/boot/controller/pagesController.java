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

    @RequestMapping("/userPage")
    public String userPage(){
        return "pages/userInfo";
    }

    @RequestMapping("/chargePage")
    public String chargePage(){
        return "pages/chargeInfo";
    }

    @RequestMapping("/addEmployeePage")
    public String addEmployee(){
        return "pages/addEmployee";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/editEmployeePage")
    public String editEmployeePage(){
        return "pages/editEmployee";
    }

    @RequestMapping("/registerCharge")
    public String registerCharge(){
        return "pages/registerCharge";
    }

}
