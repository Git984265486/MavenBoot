package com.boot.controller;


import com.boot.service.tUserService;
import com.boot.damain.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class pagesController {

    @Autowired
    private tUserService userService;

    @RequestMapping("/index")
    public String test(){
        return "index";
    }

    @RequestMapping("/testPage")
    public String test1(){
        return "pages/userInfo";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Map<String ,Object> getUserData(){
        HashMap<String , Object> map = new HashMap<>();
        List<tUser> listUser = userService.selectListUser();
        map.put("code","0");
        map.put("count",listUser.size());
        map.put("data",listUser);
        return map;
    }

}
