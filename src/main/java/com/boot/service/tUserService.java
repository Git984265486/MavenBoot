package com.boot.service;

import com.boot.damain.tUser;

import java.util.List;

public interface tUserService {

    /**【列出所有用户信息】**/
    List<tUser> selectListUser();

    /**【用户登录验证】**/
    public tUser validateUser(String username , String password);
}
