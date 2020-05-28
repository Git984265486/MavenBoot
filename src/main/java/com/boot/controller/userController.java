package com.boot.controller;

import com.boot.damain.tUser;
import com.boot.service.tUserService;
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

    /**【用户登录验证】**/
    @RequestMapping("/validateUser")
    @ResponseBody
    public  Map<String ,Object> validateUser(@RequestParam String username , @RequestParam String password){
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

    /**【登录用户提升管理与降为普通用户，密码重置】**/
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public  Map<String ,Object> updateAdmin(@RequestParam String isAdmin ,@RequestParam String optPWD ,  @RequestParam String code){
        HashMap<String , Object> map = new HashMap<>();
        System.out.println("用户身份变更传过来的数据：" + isAdmin + "\t|识别：" + code + "\t|密码：" + optPWD);
        if (code != null && !code.equals("")){
            if (!code.equals("5") && optPWD.equals("")){
                userService.updateAdmin(Integer.parseInt(isAdmin) , code);
            }else if (optPWD.equals("RS")){
                userService.updatePWD("88888888" , code);
            }
            map.put("result","success");
        }else{
            map.put("result","fail");
        }
        return map;
    }

    /**【用户修改密码】**/
    @RequestMapping("/resetPassword")
    @ResponseBody
    public Map<String ,Object> resetPassword(@RequestParam String optPWD ,  @RequestParam String code){
        HashMap<String , Object> map = new HashMap<>();
        System.out.println("用户身份变更传过来的数据：" + "\t|识别code：" + code + "\t|密码：" + optPWD);
        if (optPWD != null && !optPWD.equals("") && code != null && !code.equals("")){
            userService.updatePWD(optPWD,code);
            map.put("result","success");
        }else{
            map.put("result","fail");
        }
        return map;
    }

}
