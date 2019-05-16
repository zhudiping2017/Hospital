package com.qhit.baseModule.controller; 

import com.qhit.baseModule.pojo.BaseModule; 
import com.qhit.baseModule.service.IBaseModuleService; 
import com.qhit.baseModule.service.impl.BaseModuleServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List; 



@Controller 
@RequestMapping("/baseModule") 
public class BaseModuleController { 

    @Resource 
    IBaseModuleService baseModuleService; 

    @RequestMapping("/insert") 
    public String insert(BaseModule baseModule) { 
        baseModuleService.insert(baseModule); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer mid, HttpServletResponse response) throws IOException { 
        baseModuleService.delete(mid); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(BaseModule baseModule) { 
        baseModuleService.update(baseModule); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(BaseModule baseModule) { 
        baseModuleService.updateSelective(baseModule); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer mid, Model model) { 
        BaseModule baseModule = baseModuleService.findById(mid); 
        model.addAttribute("baseModule",baseModule); 
        return "baseModule/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<BaseModule> list = baseModuleService.findAll(); 
        model.addAttribute("list",list); 
        return "baseModule/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<BaseModule> list = baseModuleService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(BaseModule baseModule,Model model) { 
        List<BaseModule> list = baseModuleService.search(baseModule); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",baseModule); 
        return "baseModule/list"; 
    } 
 
} 
