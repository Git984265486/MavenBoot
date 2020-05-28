package com.boot.service.impl;

import com.boot.damain.tUser;
import com.boot.mapper.tUserMapper;
import com.boot.service.tUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tUserImpl implements tUserService {

    @Autowired
    private tUserMapper userMapper;

    /**【列出所有用户信息】**/
    @Override
    public List<tUser> selectListUser(){
        return userMapper.selectListUser();
    }

    /**【用户登录验证】**/
    @Override
    public tUser validateUser(String username , String password) {
        return userMapper.validateUser(username , password);
    }

    /**【用户与管理员身份变更】**/
    @Override
    public void updateAdmin(int isAdmin, String code) {
        userMapper.updateAdmin(isAdmin, code);
    }

    /**【重置账号密码】**/
    @Override
    public void updatePWD(String optPWD, String code) {
        userMapper.updatePWD(optPWD,code);
    }

}
