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

    /**【待检表格数据】**/
    @RequestMapping("/selectByInCheck")
    @ResponseBody
    public Map<String ,Object> selectByInCheck(@RequestParam(value = "page",defaultValue = "1") int page,
                                               @RequestParam(value = "limit",defaultValue = "10")int size,
                                               @RequestParam(value = "carno",defaultValue = "")String carno){
        HashMap<String , Object> mapCheck = new HashMap<>();
        PageHelper.startPage(page,size);
        List<superviserCar> listData = superviserService.selectByInCheck("0",carno);
        PageInfo<superviserCar> pageInfo = new PageInfo<>(listData);
        mapCheck.put("code",0);
        mapCheck.put("count",pageInfo.getTotal());
        mapCheck.put("data",pageInfo.getList());
        return mapCheck;
    }
    /**【待审表格数据】**/
    @RequestMapping("/selectByExamination")
    @ResponseBody
    public Map<String ,Object> selectByexamination(@RequestParam(value = "page",defaultValue = "1") int page,
                                               @RequestParam(value = "limit",defaultValue = "10")int size,
                                               @RequestParam(value = "carno",defaultValue = "")String carno){
        HashMap<String , Object> map = new HashMap<>();
        PageHelper.startPage(page,size);
        List<superviserCar> listData = superviserService.selectByInCheck("1",carno);
        PageInfo<superviserCar> pageInfo = new PageInfo<>(listData);
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    /**【发证表格数据】**/
    @RequestMapping("/selectByComplete")
    @ResponseBody
    public Map<String ,Object> selectBycomplete(@RequestParam(value = "page",defaultValue = "1") int page,
                                                @RequestParam(value = "limit",defaultValue = "10")int size,
                                                @RequestParam(value = "carno",defaultValue = "")String carno){
        HashMap<String , Object> mapComplete = new HashMap<>();
        PageHelper.startPage(page,size);
        List<superviserCar> listData = superviserService.selectByInCheck("2",carno);
        PageInfo<superviserCar> pageInfo = new PageInfo<>(listData);
        mapComplete.put("code",0);
        mapComplete.put("count",pageInfo.getTotal());
        mapComplete.put("data",pageInfo.getList());
        return mapComplete;
    }

    /**【通过KeyID更新车辆检查状态】**/
    @RequestMapping("/updateCarStatusByKeyID")
    @ResponseBody
    public Map<String , Object> updateCarStatusByKeyID(@RequestParam String keyID) throws ParseException {
        HashMap<String , Object> map = new HashMap<>();
        String result = "";
        if (keyID != null && !keyID.equals("")){
            superviserCar car = superviserService.selectDetailBykeyID(keyID);   //通过KeyID查询数据
            if (car != null){
                superviserCarTools tool = new superviserCarTools();
                superviserCar updateData = tool.updateCarData(car);             //计算各项检测耗时数据
                superviserService.updateCarStatus(updateData);                  //将计算出的各项数据更新到数据库
                result = "updateSuccess";
            }else {
                result = "updateFail";
            }
        }
        map.put("result",result);
        return map;
    }

    /**【通过KeyID获取车辆检查状态信息】**/
    @RequestMapping("/selectDetailBykeyID")
    @ResponseBody
    public Map<String , Object> selectDetailBykeyID(@RequestParam String keyID) throws ParseException {
        HashMap<String , Object> map = new HashMap<>();
        String result = "";
        if (keyID != null && !keyID.equals("")){
            superviserCar car = superviserService.selectDetailBykeyID(keyID);   //通过KeyID查询数据
            if (car != null){
                superviserCarTools tool = new superviserCarTools();
                superviserCar updateData = tool.updateCarData(car);             //计算各项检测耗时数据
                map.put("detailData",updateData);
                result = "Success";
            }else {
                result = "Fail";
            }
        }
        map.put("result",result);
        return map;
    }
}
