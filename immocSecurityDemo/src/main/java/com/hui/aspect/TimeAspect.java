package com.hui.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: CarlChen
 * @Despriction: 时间切片  切片的demo
 * @Date: Create in 17:43 2019\2\7 0007
 */
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.hui.controller.DemoControllerFirst.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aspect start time.....pjp = [" + pjp + "]");

        long time = new Date().getTime();

        Object[] args = pjp.getArgs();
        for (Object arg : args){
            System.out.println("arg...." + arg);
        }

        Object proceed = pjp.proceed();

        System.out.println("TimeAspect spend time...." + (new Date().getTime() - time));

        System.out.println("aspect end time.....pjp = [" + pjp + "]");

        return proceed;
    }

}
