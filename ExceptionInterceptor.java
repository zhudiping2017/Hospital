package com.qhit.common;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;


@Component
@Aspect
public class ExceptionInterceptor {

    @AfterThrowing(value="execution(* com.qhit.*.controller.*.*(..))" ,throwing = "e")
    public void handlerException(Exception e){
//      将e.printStackTrace()中的信息放到StringWriter输出流中
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
//        将字符串按照”\r\n”分割，并且只显示前四行
        String[] arr = str.split("\r\n");
        int len = arr.length>4?4:arr.length;
        for (int i = 0; i <len ; i++) {
            System.out.println(arr[i]);
        }
    }


}
