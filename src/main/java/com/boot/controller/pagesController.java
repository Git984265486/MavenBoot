package com.boot.controller;


import com.boot.service.tUserService;
import com.boot.damain.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class pagesController {

    @Autowired
    private tUserService userService;

    @RequestMapping("/hello")
    public String test(){
        return "Hello Word!";
    }

    @RequestMapping("/user")
    public Map<String ,Object> getUserData(){
        HashMap<String , Object> map = new HashMap<>();
        List<tUser> listUser = userService.selectListUser();
        map.put("listUser",listUser);
        return map;
    }

}
