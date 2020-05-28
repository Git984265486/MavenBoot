package com.boot.damain;

import lombok.Data;

@Data
public class base {

    private int autoid;     //自增id
    private String code;    //编码
    private String name;    //
    private String py;      //助记码
    private int stop;       //是否停用
    private String memo;    //备注
    private int ccode;
    private String spdl;
    private int sfbz;       //收费标准

}
