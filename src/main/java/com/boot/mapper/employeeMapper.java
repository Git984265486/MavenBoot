package com.boot.mapper;

import com.boot.damain.employee;

import java.util.List;

public interface employeeMapper {

    /**【查询全部员工信息】**/
    public List<employee> selectListEmployee();

    /**【添加员工，同时添加用户】**/
    public void addEmployee(String code , String name , String py , int deptid);

    /**【取最后一条数据】**/
    public employee selectLastData();

}
