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



}
