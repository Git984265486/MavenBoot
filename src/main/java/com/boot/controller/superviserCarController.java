package com.boot.controller;

import com.boot.damain.superviserCar;
import com.boot.service.superviserCarService;
import com.boot.tools.staticTools;
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
        List<superviserCar> listData = new ArrayList<>();
        System.out.println("【carNo的值：】" + carNo);
        if (carNo != null || !carNo.equals("") ){
            listData = superviserService.selectDataByCarNo(carNo);
        }else {
            listData = superviserService.seleListData();
        }
        PageInfo<superviserCar> pageInfo = new PageInfo<>(listData);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        map.put("code",0);

        return map;
    }

    /**【检验发证表格数据】**/
    @RequestMapping("/selectTableData")
    @ResponseBody
    public Map<String ,Object> selectByInCheck(@RequestParam(value = "page",defaultValue = "1") int page,
                                               @RequestParam(value = "limit",defaultValue = "10")int size,
                                               @RequestParam(value = "carno",defaultValue = "")String carno,
                                               @RequestParam String DataType){
        HashMap<String , Object> mapCheck = new HashMap<>();
        PageHelper.startPage(page,size);
        System.out.println("【数据类型】" + DataType);
        List<superviserCar> listData = new ArrayList<>();
        if (DataType != null && !DataType.equals("")){

            if (DataType.equals("0")){
                listData = superviserService.selectByInCheck("0",carno);
            }else if (DataType.equals("1")){
                listData = superviserService.selectByInCheck("1",carno);
            }else if (DataType.equals("2")){
                listData = superviserService.selectByInCheck("2",carno);
            }
        }
        PageInfo<superviserCar> pageInfo = new PageInfo<>(listData);
        mapCheck.put("code",0);
        mapCheck.put("count",pageInfo.getTotal());
        mapCheck.put("data",pageInfo.getList());
        return mapCheck;
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


    /**【通过KeyID更新车辆检查状态】**/
    @RequestMapping("/updateCarStatusByKeyID")
    @ResponseBody
    public Map<String , Object> updateCarStatusByKeyID(@RequestParam String keyID ,
                                                       @RequestParam String instruction) throws ParseException {
        HashMap<String , Object> map = new HashMap<>();
        String result = "";
        if (keyID != null && !keyID.equals("")){
            superviserCar car = superviserService.selectDetailBykeyID(keyID);   //通过KeyID查询数据
            if (car != null && instruction != null && !instruction.equals("")){
                superviserCarTools tool = new superviserCarTools();
                superviserCar updateData = tool.updateCarData(car);             //计算各项检测耗时数据
                if (instruction.equals("check")){                   //待检到待审
                    updateData.setIn_check("1");
                }else if (instruction.equals("examination")){       //待审到发证
                    updateData.setIn_check("2");
                }else if (instruction.equals("complete")){          //发证广播播报
                    updateData.setResult("1");
                    System.out.println("【进行广播播报发证】");
                }
                superviserService.updateCarStatus(updateData);                  //将计算出的各项数据更新到数据库
                result = "updateSuccess";
            }else {
                result = "updateFail";
            }
        }
        map.put("result",result);
        return map;
    }

    /**【通过车牌号码更新车辆检查状态】**/
    @RequestMapping("/updateCarByCarNo")
    @ResponseBody
    public Map<String , Object> updateCarStatusByKeyID(@RequestParam String carNo,@RequestParam String command,
                                                       @RequestParam String operater) throws ParseException {
        HashMap<String , Object> map = new HashMap<>();
        String result = "wait";
        System.out.println("【车牌】" + carNo + "\t【操作指令】" + command +"\t【操作者】" + operater);
        if (carNo != null && !carNo.equals("")){
            List<superviserCar> listData = superviserService.selectByCarNo(carNo);  //通过车牌获取数据
            if (listData!=null){
                superviserCarTools tool = new superviserCarTools();
                timeTools time = new timeTools();                       //时间处理工具类
                for (int i = 0 ; i < listData.size() ; i++){
                    superviserCar updateData = listData.get(i);         //拿到原始数据
                    if (command != null && !command.equals("")){
                        if (command.equals("WJS")){                     //外检开始
                            updateData.setWj_start(time.getNowTime());  //加入外检开始时间
                            updateData = tool.updateCarData(updateData);//处理过的数据
                        }else if (command.equals("WJE")){               //外检结束
                            updateData.setWj_end(time.getNowTime());
                            updateData = tool.updateCarData(updateData);//处理过的数据
                            System.out.println("【---外检结束耗时---】" + updateData.getWj_usetime());
                        }else if (command.equals("AJS")){               //安检开始
                            updateData.setAj_start(time.getNowTime());
                            updateData = tool.updateCarData(updateData);//处理过的数据
                        }else if (command.equals("AJE")){               //安检结束
                            updateData.setAj_end(time.getNowTime());
                            updateData = tool.updateCarData(updateData);//处理过的数据
                            System.out.println("【---安检结束耗时---】" + updateData.getAj_usetime());
                        }else if (command.equals("HJS")){               //环检开始
                            updateData.setHj_start(time.getNowTime());
                            updateData = tool.updateCarData(updateData);//处理过的数据
                        }else if (command.equals("HJE")){               //环检结束
                            updateData.setHj_end(time.getNowTime());
                            updateData.setHj_usetime(updateData.getHj_usetime());
                            updateData = tool.updateCarData(updateData);//处理过的数据
                            System.out.println("【---环检结束耗时---】" + updateData.getHj_usetime());
                        }
                    }
                    superviserService.updateCarByCarNo(updateData);
                    result = "success";
                }
            }
        }
        map.put("result",result);
        return map;
    }


    /**【广播领证】**/
    @RequestMapping("/finishCheck")
    @ResponseBody
    public Map<String ,Object> finishCheck(@RequestParam(defaultValue = "",value = "speakText")String speakText){
        HashMap<String,Object> map = new HashMap<>();
        String result = "";
        System.out.println("语音播报内容：" + speakText);
        if (!speakText.equals("")){
            boolean isSpeak = staticTools.speakingText(speakText);
            if (isSpeak){
                result = "success";
            }
        }
        map.put("result",result);
        return map;
    }


    /**【查询当天录入两小时未完成检测车辆车牌】**/
    @RequestMapping("/selectTwoHourDataList")
    @ResponseBody
    public Map<String ,Object> selectTwoHour(){
        HashMap<String,Object> map = new HashMap<>();
        String result = "";
        List<superviserCar> listDataCarNo = superviserService.selectTwoHour();
        //superviserCarTools tools = new superviserCarTools();
        //List<String> listCarNo = tools.getCarNoList(listDataCarNo);
        if (listDataCarNo.size() != 0 ){
            result = "listData";
        }else {
            result = "noneData";
        }
        map.put("code",0);
        map.put("result",result);
        map.put("data",listDataCarNo);
        return map;
    }
}
