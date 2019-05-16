package com.qhit.baseFunction.controller;

import com.alibaba.fastjson.JSON;
import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseFunction.service.IBaseFunctionService;
import com.qhit.baseFunction.service.impl.BaseFunctionServiceImpl;
import com.qhit.baseModule.pojo.BaseModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/baseFunction")
public class BaseFunctionController {
    @Resource
    private IBaseFunctionService baseFunctionService;
    @RequestMapping("/list")
    public String list(Model model){
        List<BaseFunction> list = baseFunctionService.findAll();
        model.addAttribute("list",list);
        return "baseFunction/list";
    }
    @RequestMapping("/insert")
    public String insert(BaseFunction baseFunction){
        if(baseFunction.getUrl()!=null){
            baseFunction.setUrl(baseFunction.getUrl().trim());
        }
        baseFunctionService.insert(baseFunction);
        return "forward:list.action";
    }
    @RequestMapping("/load")
    public String load(BaseFunction baseFunction,Model model){
        baseFunction = baseFunctionService.findById(baseFunction.getFid());
        model.addAttribute("baseFunction",baseFunction);
        return "baseFunction/edit";
    }
    @RequestMapping("/update")
    public String update(BaseFunction baseFunction){
        baseFunctionService.update(baseFunction);
        return "forward:list.action";
    }
    @RequestMapping("/delete")
    public String delete(BaseFunction baseFunction){
        baseFunctionService.delete(baseFunction.getFid());
        return "forward:list.action";
    }

    //通过ajax获取top数据
    @RequestMapping("/ajaxList")
    public void ajaxList(HttpServletResponse response,Integer mid,Integer userId) throws IOException {
        if(mid==null){
            mid=1;
        }
        List<BaseFunction> list = baseFunctionService.findByMid(mid,userId);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }

}
