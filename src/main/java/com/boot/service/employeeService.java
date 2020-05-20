package com.boot.service;

import com.boot.damain.employee;

import java.util.Date;
import java.util.List;

public interface employeeService {

    /**【查询全部员工信息】**/
    public List<employee> selectListEmployee();

    /**【添加员工，同时添加用户】**/
    public void addEmployee(employee employee);

    /**【取最后一条数据】**/
    public employee selectLastData();

    /**【通过code删除员工信息】**/
    public void delEmployee(String code);

    /**【更新员工信息，更新字段:stop】**/
    public void stopEmployee(String code , int stop);

}
