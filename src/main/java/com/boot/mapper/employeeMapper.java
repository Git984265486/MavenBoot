package com.boot.mapper;

import com.boot.damain.employee;

import java.util.Date;
import java.util.List;

public interface employeeMapper {

    /**【查询全部员工信息】**/
    public List<employee> selectListEmployee();

    /**【添加员工，同时添加用户】**/
    public void addEmployee(String code , String name , String py , int deptid,String sex, String birthday,
                            String cardno,String address,String phone,String mobile,String memo);

    /**【取最后一条数据】**/
    public employee selectLastData();

    /**【通过code删除员工信息】**/
    public void delEmployee(String code);

    /**【更新员工信息，更新字段:stop】**/
    public void stopEmployee(String code , int stop);

    /**【通过code拿到员工信息】**/
    public employee selectEmployeeByCode(String code);

    /**【更新员工信息】**/
    public void editEmployee(String birthday,String cardno,String address,String phone,String mobile,String memo,String sex,String code);
}
