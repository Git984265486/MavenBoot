package com.boot.controller;

import com.boot.damain.tUser;
import com.boot.service.tUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class userController {

    @Autowired
    private tUserService userService;

    /**【列出所有用户信息】**/
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

    @RequestMapping("/validateUser")
    @ResponseBody
    public  Map<String ,Object> validateUser(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam String username , @RequestParam String password){
        HashMap<String , Object> map = new HashMap<>();
        System.out.println("传过来的数据：" + username + "\t|" + password);

        if (username != null && !username.equals("") && password != null && !password.equals("")){
            tUser user = userService.validateUser(username,password);
            if (user != null){
                System.out.println("拿到的数据：\t" + user.getOptName() + "\nStop:\t" + user.getStop());
                map.put("user",user);
                map.put("result","success");
            }else{
                map.put("result","fail");
            }
        }else{
            map.put("result","fail");
        }
        return map;
    }

}
