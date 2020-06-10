package com.boot.damain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tSFMXB")
public class chargeOther {

    private int autoid;
    private String jcx;
    private int sl;
    private int dj;
    private int je;
    private String mxmemo;
    private String pcode;

}
