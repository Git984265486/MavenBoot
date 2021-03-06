package com.boot.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域问题
public class pagesController {

    /**【登录页面】**/
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    /**【首页】**/
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**【员工信息页面】**/
    @RequestMapping("/userPage")
    public String userPage(){
        return "pages/userInfo";
    }

    /**【收费记录页面】**/
    @RequestMapping("/chargePage")
    public String chargePage(){
        return "pages/chargeInfo";
    }

    /**【员工添加页面】**/
    @RequestMapping("/addEmployeePage")
    public String addEmployee(){
        return "pages/addEmployee";
    }

    /**【员工信息项编辑页面】**/
    @RequestMapping("/editEmployeePage")
    public String editEmployeePage(){
        return "pages/editEmployee";
    }

    /**【收费登记页面】**/
    @RequestMapping("/registerCharge")
    public String registerCharge(){
        return "pages/registerCharge";
    }

    /**【收费项编辑页面】**/
    @RequestMapping("/editBasePage")
    public String editBasePage(){
        return "pages/editBase";
    }

    /**【收费项添加页面】**/
    @RequestMapping("/addBasePage")
    public String addBasePage(){
        return "pages/addBase";
    }

    /**【检测发证页面】**/
    @RequestMapping("/completePage")
    public String completePage(){
        return "pages/completeCheck";
    }

    /**【车辆监测页面】**/
    @RequestMapping("/superviserCarPage")
    public String superviserPage(){
        return "pages/superviserCar";
    }

    /**【车辆监测详细页面】**/
    @RequestMapping("/superviserDetailPage")
    public String superviserDetail(){
        return "pages/superviserDetail";
    }

    /**【数据模拟页面】**/
    @RequestMapping("/dataTestPage")
    public String dataTestPage(){
        return "pages/testData";
    }

    /**【数据统计页面】**/
    @RequestMapping("/statisticsTablePage")
    public String statisticsTablePage(){
        return "pages/statisticsTable";
    }

    /**【右下角弹出页面】**/
    @RequestMapping("/tipsPage")
    public String tipsPage(){
        return "pages/tipsCheck";
    }

    /**【数据管理首页】**/
    @RequestMapping("/dataIndexPage")
    public String dataIndexPage(){
        return "pages/dataIndex";
    }

    /**【收费信息数据页面】**/
    @RequestMapping("/dataManagementPage")
    public String dataManagementPage(){
        return "pages/dataManagement";
    }

    /**【监管车辆数据页面】**/
    @RequestMapping("/dataSuperviserCar")
    public String dataSuperViserCar(){
        return "pages/dataSuperviserCar";
    }

    /**【收费信息tSFMXB表数据页面】**/
    @RequestMapping("/chargeOtherPage")
    public String chargeOtherPage(){
        return "pages/dataChargeOther";
    }
}
