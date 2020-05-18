package com.boot.service.impl;

import com.boot.damain.employee;
import com.boot.mapper.employeeMapper;
import com.boot.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class employeeImpl implements employeeService {

    @Autowired
    private employeeMapper empMapper;

    @Override
    public List<employee> selectListEmployee() {
        return empMapper.selectListEmployee();
    }

    /**【添加员工，同时添加用户】**/
    @Override
    public void addEmployee(String code , String name , String py , int deptid){
        empMapper.addEmployee(code,name,py,deptid);
    }

    /**【取最后一条数据】**/
    @Override
    public employee selectLastData(){
        return empMapper.selectLastData();
    }

}
