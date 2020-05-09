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

    @Override
    public List<tUser> selectListUser(){
        return userMapper.selectListUser();
    }

}
