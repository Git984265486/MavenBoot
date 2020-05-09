package com.boot.damain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tUser")
public class tUser {

    @TableField("AutoID")
    private int AutoID;
    private String OptCode;
    private String OptName;
    private String OptPY;
    private String OptPWD;
    private int bAdmin;
    private int Stop;

}
