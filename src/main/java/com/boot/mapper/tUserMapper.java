package com.boot.mapper;


import com.boot.damain.tUser;

import java.util.List;

public interface tUserMapper{

    /**【列出所有用户信息】**/
    public List<tUser> selectListUser();

    /**【用户登录验证】**/
    public tUser validateUser(String username , String password);

}
