package com.qhit.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Aspect
@Component
public class LogInterceptor {
    @Before("execution(* com.qhit.*.controller.*.*(..))")
    public void before(JoinPoint jp) {
//        类名
        String className  = jp.getTarget().getClass().getName();
//        方法名
        String methodName = jp.getSignature().getName();
//        参数
        String args = Arrays.toString(jp.getArgs());
//        记录时间
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());
        System.out.println(time+"开始执行类名："+className+"下的方法"+methodName+",输入参数是："+args);
    }
    @AfterReturning(returning = "result",value = "execution(* com.qhit.*.controller.*.*(..))")
    public void afterReturning(JoinPoint jp,Object result) {
        //        类名
        String className  = jp.getTarget().getClass().getName();
        //        方法名
        String methodName = jp.getSignature().getName();
//        记录时间
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());
        System.out.println(time+"结束执行类名："+className+"下的方法"+methodName+",返回值是："+result);
    }
}
