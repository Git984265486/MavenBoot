package com.boot.controller;

import com.boot.damain.chargeInfo;
import com.boot.damain.tUser;
import com.boot.tools.chargeTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class chargeController {

    @Autowired
    private com.boot.service.chargeService chargeService;

    @RequestMapping("/listChargeInfo")
    @ResponseBody
    public Map<String , Object> ListChargeInfo(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "page",defaultValue = "1") int page,
                                               @RequestParam(value = "limit",defaultValue = "20")int size,
                                               @RequestParam(value = "searchParams",defaultValue = "") String searchParams)
            throws ParseException, IOException, ServletException {
        HashMap<String , Object> map = new HashMap<>();
        PageHelper.startPage(page,size);
        List<chargeInfo> listCharge = new ArrayList<>();

        if (searchParams == null || searchParams.equals("")){
            listCharge = chargeService.selectChargeList();
        }else{
            chargeTools tool = new chargeTools(searchParams);
            listCharge = chargeService.filterChargeList(tool.startTimeDate,tool.endTimeDate,tool.billno ,tool.carno,
                    tool.cartype ,tool.cz,tool.jcxm,tool.memo);
        }
        PageInfo<chargeInfo>pageInfo = new PageInfo<>(listCharge);
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

}
