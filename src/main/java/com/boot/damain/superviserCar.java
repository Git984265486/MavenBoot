package com.boot.damain;

import lombok.Data;

@Data
public class superviserCar {

    private int keyID;
    private String carno;
    private String in_check;
    private String wj_start;
    private String wj_end;
    private String aj_start;
    private String aj_end;
    private String hj_start;
    private String hj_end;
    private String start_time;
    private String end_time;
    private String wj_usetime;
    private String aj_usetime;
    private String hj_usetime;
    private String usetime;
    private String operator;
    private String result;

}
