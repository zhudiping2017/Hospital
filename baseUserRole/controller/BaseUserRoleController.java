package com.qhit.baseUserRole.controller; 

import com.qhit.baseUserRole.pojo.BaseUserRole; 
import com.qhit.baseUserRole.service.IBaseUserRoleService; 
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl; 
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
@RequestMapping("/baseUserRole") 
public class BaseUserRoleController { 

    @Resource 
    IBaseUserRoleService baseUserRoleService; 

    @RequestMapping("/insert") 
    public String insert(BaseUserRole baseUserRole) { 
        baseUserRoleService.insert(baseUserRole); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer urid, HttpServletResponse response) throws IOException { 
        baseUserRoleService.delete(urid); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(BaseUserRole baseUserRole) { 
        baseUserRoleService.update(baseUserRole); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(BaseUserRole baseUserRole) { 
        baseUserRoleService.updateSelective(baseUserRole); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer urid, Model model) { 
        BaseUserRole baseUserRole = baseUserRoleService.findById(urid); 
        model.addAttribute("baseUserRole",baseUserRole); 
        return "baseUserRole/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<BaseUserRole> list = baseUserRoleService.findAll(); 
        model.addAttribute("list",list); 
        return "baseUserRole/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<BaseUserRole> list = baseUserRoleService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(BaseUserRole baseUserRole,Model model) { 
        List<BaseUserRole> list = baseUserRoleService.search(baseUserRole); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",baseUserRole); 
        return "baseUserRole/list"; 
    } 
 
} 
