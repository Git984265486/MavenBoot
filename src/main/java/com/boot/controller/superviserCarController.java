package com.boot.controller;

import com.boot.damain.superviserCar;
import com.boot.service.superviserCarService;
import com.boot.tools.superviserCarTools;
import com.boot.tools.timeTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class superviserCarController {
    @Autowired
    private superviserCarService superviserService;

    /**【获取30天内的数据】**/
    @RequestMapping("/seleListData")
    @ResponseBody
    public Map<String , Object> seleListData(@RequestParam(value = "page",defaultValue = "1") int page,
                                             @RequestParam(value = "limit",defaultValue = "10")int size,
                                             @RequestParam(value = "carNo",defaultValue = "")String carNo){
        HashMap<String , Object> map = new HashMap<>();
        PageHelper.startPage(page,size);
        List<superviserCar> listData = superviserService.seleListData();
        System.out.println("【carNo的值：】" + carNo);
        if (carNo != null || !carNo.equals("") ){
            listData = superviserService.selectDataByCarNo(carNo);
        }
        PageInfo<superviserCar> pageInfo = new PageInfo<>(listData);
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**【通过车牌号码更新车辆检查状态】**/
    @RequestMapping("/updateCarStatusByKeyID")
    @ResponseBody
    public Map<String , Object> updateCarStatusByKeyID(/*@RequestParam String operateStr ,*/ @RequestParam String carno) throws ParseException {
        HashMap<String , Object> map = new HashMap<>();
        if (carno != null && !carno.equals("")){
            superviserCar car = superviserService.selectDetailByCarNo(carno);   //通过车牌号码查询数据
            if (car != null){
                superviserCarTools tool = new superviserCarTools();
                superviserCar updateData = tool.updateCarData(car);             //计算各项检测耗时数据
                System.out.println("【拿到外检耗时】" + updateData.getWj_usetime());
                System.out.println("【拿到安检耗时】" + updateData.getAj_usetime());
                System.out.println("【拿到环检耗时】" + updateData.getHj_usetime());
                System.out.println("【拿到总耗时】" + updateData.getUsetime());
            }
        }

        return map;
    }


}
