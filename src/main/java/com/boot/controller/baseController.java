package com.boot.controller;

import com.boot.damain.base;
import com.boot.service.baseService;
import com.boot.tools.baseTools;
import com.boot.tools.employeeTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class baseController {

    @Autowired
    private baseService baseService;


    /**【查询全部数据】**/
    @RequestMapping("/listBaseData")
    @ResponseBody
    public Map<String , Object> listBaseData(@RequestParam(value = "page",defaultValue = "1") int page,
                                             @RequestParam(value = "limit",defaultValue = "10")int size){
        HashMap<String , Object> map = new HashMap<>();
        PageHelper.startPage(page,size);
        //if (!code.equals("") && code != null){
        //System.out.println("--->【code：" + code + "\tpage:" + page +"】<---");
        //}
        List<base> baseList = baseService.selectBaseList();
        baseTools tools = new baseTools();
        PageInfo<base> pageInfo = new PageInfo<>(tools.listBase(baseList));
        map.put("code",0);
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**【查询最后操作的一条数据】**/
    @RequestMapping("/lastBaseData")
    @ResponseBody
    public Map<String , Object> lastBaseData(){
        HashMap<String , Object> map = new HashMap<>();
        base base = baseService.lastBaseData();
        baseTools tools = new baseTools();
        map.put("code" , tools.baseCode(base.getCode()));
        return map;
    }

    /**【通过code查询数据】**/
    @RequestMapping("/selBaseByCode")
    @ResponseBody
    public Map<String , Object> selBaseByCode(@RequestParam String code){
        HashMap<String , Object> map = new HashMap<>();
        System.out.println("【删除base表格数据传过来的code】" + code);
        if (code != null && !code.equals("")){
            base base = baseService.selBaseByCode(code);
            if (base.getStop().equals("1")){
                base.setStop("是");
            }else {
                base.setStop("否");
            }
            map.put("baseData",base);
            map.put("result","success");
        }else{
            map.put("result","fail");
        }
        return map;
    }

    /**【通过code删除数据】**/
    @RequestMapping("/delBaseByCode")
    @ResponseBody
    public Map<String , Object> delBaseByCode(@RequestParam String code){
        HashMap<String , Object> map = new HashMap<>();
        System.out.println("【删除base表格数据传过来的code】" + code);
        if (code != null && !code.equals("")){
            baseService.delBaseByCode(code);
            map.put("result","success");
        }else{
            map.put("result","fail");
        }
        return map;
    }

    /**【通过code更新数据】**/
    @RequestMapping("/updateBaseByCode")
    @ResponseBody
    public Map<String ,Object> updateBaseByCode(@RequestParam String updateData,@RequestParam String operateStr){
        HashMap<String , Object> map = new HashMap<>();
        System.out.println("【更新收费项目表传过来的数据updateData】"+ updateData + "\t【操作字段operateStr】" + operateStr);
        if (updateData != null && !updateData.equals("")){      //更新的字段
            if (operateStr != null && !operateStr.equals("")){  //更新指令
                if (operateStr.equals("editData")){             //修改数据
                    baseTools tools = new baseTools();
                    base baseData = tools.baseJsonData(updateData);
                    baseService.updateBaseByCode(baseData);
                    map.put("result","editSuccess");
                }else if (operateStr.equals("stopData")){       //停用数据
                    base stopBase = baseService.selBaseByCode(updateData);
                    if (stopBase != null){
                        stopBase.setStop("1");
                        baseService.updateBaseByCode(stopBase);
                        map.put("result","stopSuccess");
                    }
                }else if (operateStr.equals("startData")){      //启用数据
                    base startBase = baseService.selBaseByCode(updateData);
                    if (startBase != null){
                        startBase.setStop("0");
                        baseService.updateBaseByCode(startBase);
                        map.put("result","startSuccess");
                    }
                }else {
                    map.put("result","fail");
                }
            }
        }else {
            map.put("result","fail");
        }
        return map;
    }

    /**【插入数据】**/
    @RequestMapping("/insertBaseData")
    @ResponseBody
    public Map<String ,Object> insertBaseData(@RequestParam String insertData){
        HashMap<String ,Object> map = new HashMap<>();
        System.out.println("【插入收费项目表传过来的数据insertData】"+ insertData );
        if (insertData != null && !insertData.equals("")){
            baseTools tool = new baseTools();
            base baseData = tool.insertJsonData(insertData);
            if (baseData != null){
                base base = baseService.lastBaseData();
                baseData.setCode(tool.baseCode(base.getCode()));    //给code赋值
                baseService.insertBaseData(baseData);
                System.out.println("【code值】\t" + baseData.getCode() + "\t【name值】\t" + baseData.getName());
                map.put("result","insertSuccess");
            }
        }else{
            map.put("result","fail");
        }
        return map;
    }
}
