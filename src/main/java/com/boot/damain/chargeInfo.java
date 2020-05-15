package com.boot.damain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tSFB")
public class chargeInfo {

    @TableField("autoid")
    private int autoid;     //自增ID
    private String billno;  //编号
    private String carno;   //车牌号码
    private Date dtdate;    //录入时间
    private String cartype; //车辆类型
    private String cz;      //支付方式
    private String czphone; //
    private String pcode;   //车辆识别码
    private String memo;    //备注信息
    private String jcxm;     //检测项目
    private int zsl;        //总数量
    private int zje;        //总金额
    private String optname; //操作人
    private int flag;       //
    private Date dsdate;    //
    private Date dldate;    //

}
