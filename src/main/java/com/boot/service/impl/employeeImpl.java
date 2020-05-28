package com.boot.service.impl;

import com.boot.damain.employee;
import com.boot.mapper.employeeMapper;
import com.boot.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class employeeImpl implements employeeService {

    @Autowired
    private employeeMapper empMapper;

    /**【获取所有的员工信息】**/
    @Override
    public List<employee> selectListEmployee() {
        return empMapper.selectListEmployee();
    }

    /**【添加员工，同时添加用户】**/
    @Override
    public void addEmployee(employee employee){
        empMapper.addEmployee(employee.getCode(),employee.getName(),employee.getPy(),employee.getDeptid(),
                employee.getSex(),employee.getBirthday(),employee.getCardno(),employee.getAddress(),
                employee.getPhone(),employee.getMobile(),employee.getMemo());
    }

    /**【取最后一条数据】**/
    @Override
    public employee selectLastData(){
        return empMapper.selectLastData();
    }

    /**【通过code删除员工信息】**/
    @Override
    public void delEmployee(String code) {
        empMapper.delEmployee(code);
    }

    /**【更新员工信息，更新字段:stop】**/
    @Override
    public void stopEmployee(String code, int stop) {
        empMapper.stopEmployee(code , stop);
    }

    /**【通过code拿到员工信息】**/
    @Override
    public employee selectEmployeeByCode(String code) {
        return empMapper.selectEmployeeByCode(code);
    }


    /**【通过code更新员工信息】**/
    @Override
    public void editEmployee(employee employee) {
        empMapper.editEmployee(employee.getBirthday(),employee.getCardno(),employee.getAddress(),employee.getPhone(),
                employee.getMobile(),employee.getMemo(),employee.getSex(),employee.getCode());
    }

}
