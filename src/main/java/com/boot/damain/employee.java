package com.boot.damain;

import lombok.Data;

import java.util.Date;

/**【员工信息】**/
@Data
public class employee {

    private int autoid;             //自增id（主键）
    private String code;            //员工编号
    private String name;            //姓名
    private String py;              //姓名首字母拼音
    private String sex;             //性别
    private Date birthday;          //生日
    private int deptid;             //部门ID
    private String duty;            //
    private String cardno;          //身份证号码
    private String address;         //住址
    private String phone;           //座机
    private String mobile;          //手机
    private int stop;               //是否停用（0：否  1：是）
    private String memo;            //备注
    private String wxdj;            //
    private int SMS;                //
    private String department;      //职务

}
