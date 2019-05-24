package com.qhit.common;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseUser.pojo.BaseUser;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;


@Component
@Aspect
public class AuthorityInterceptor {

    @Before("execution(* com.qhit.*.controller.*.*(..))")
    public void before() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        获取session
        HttpSession session = request.getSession();
        BaseUser user = (BaseUser) session.getAttribute("sessionUser");
        if(user!=null){
    //            用户发送的请求
            String uri = request.getRequestURI();
    //        判断该用户是否拥有该权限
    //        查询用户包含哪里权限
            List<BaseFunction> functionList = user.getBaseFunctionList();
            boolean flag = false;
            for(BaseFunction function:functionList){
                if(function.getUrl()!=null&&uri.indexOf(function.getUrl())!=-1){
                    flag=true;
                }
            }
            request.setAttribute("qx",flag);
        }
    }

}
