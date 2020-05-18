package com.boot.controller;

import com.boot.damain.tUser;
import com.boot.service.tUserService;
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
public class userController {

    @Autowired
    private tUserService userService;

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
