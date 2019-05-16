package com.qhit.medicinePurchaseInfo.controller; 

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.common.CommonUtil;
import com.qhit.medicineInstock.pojo.MedicineInstock;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService; 
import com.qhit.medicinePurchaseInfo.service.impl.MedicinePurchaseInfoServiceImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineReqPlan.service.IMedicineReqPlanService;
import com.qhit.medicineReqPlan.service.impl.MedicineReqPlanServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/06
*/
@Controller 
@RequestMapping("/medicinePurchaseInfo") 
public class MedicinePurchaseInfoController { 
    @Resource
    IMedicinePurchaseInfoService medicinePurchaseInfoService;

    @RequestMapping("/insert") 
    public String insert(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.insert(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer pchId, HttpServletResponse response) throws IOException { 
        medicinePurchaseInfoService.delete(pchId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.update(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicinePurchaseInfo medicinePurchaseInfo) {
        medicinePurchaseInfo.setPchTotal(medicinePurchaseInfo.getPchPrice()*medicinePurchaseInfo.getPchAmt());
        medicinePurchaseInfoService.updateSelective(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer pchId, Model model) { 
        MedicinePurchaseInfo medicinePurchaseInfo = medicinePurchaseInfoService.findById(pchId); 
        model.addAttribute("medicinePurchaseInfo",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll(); 
        model.addAttribute("list",list); 
        return "medicinePurchaseInfo/list"; 
    }

    @RequestMapping("/apprvlist")
    public String apprvlist(Model model) throws IOException {
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll();
        model.addAttribute("list",list);
        return "medicinePurchaseInfo/apprvlist";
    }

    /**
     * 采购计划状态为2的记录  即：待入库记录
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/preInstockList")
    public String preInstockList(Model model) throws IOException {
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.preInstockList();
        model.addAttribute("list",list);
        return "medicineInstock/preInstockList";
    }

    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicinePurchaseInfo medicinePurchaseInfo,Model model) { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.search(medicinePurchaseInfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/list"; 
    }

    @RequestMapping("/collect")
    public String collect(HttpSession session) {
        medicinePurchaseInfoService.collect(session);
        return "forward:list.action";
    }

    @RequestMapping("/updateBantch")
    public String updateBantch(HttpServletRequest request,HttpSession session) {
        String[] pchIds = request.getParameterValues("pchId");
        MedicinePurchaseInfo info = new MedicinePurchaseInfo();
        for (String pchId:pchIds){
            info.setPchId(Integer.parseInt(pchId));
            info.setStatus(2);
            info.setApprvUserid(CommonUtil.getUserId(session));
            info.setApprvDate(CommonUtil.dateToStr(new Date()));
            medicinePurchaseInfoService.updateSelective(info);
        }
        return "forward:apprvlist.action";
    }
    @RequestMapping("/instock")
    public String instock(HttpServletRequest request, Model model) {
        medicinePurchaseInfoService.instock(request);
        return "forward:preInstockList.action";
    }
 
} 
