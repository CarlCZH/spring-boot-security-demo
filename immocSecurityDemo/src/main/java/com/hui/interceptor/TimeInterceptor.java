package com.hui.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: CarlChen
 * @Despriction: 时间拦截器, 拦截器的demo
 * @Date: Create in 17:09 2019\2\7 0007
 */

//@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle.......request = [" + request + "], response = [" + response + "], handler = [" + handler + "]");
        System.out.println(((HandlerMethod)(handler)).getBean().getClass().getName());
        System.out.println(((HandlerMethod)(handler)).getMethod().getName());

        request.setAttribute("startTime", new Date().getTime());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle.......request = [" + request + "], response = [" + response + "], handler = [" + handler + "], modelAndView = [" + modelAndView + "]");

        Long startTime = (Long) request.getAttribute("startTime");

        System.out.println("postHandle spend time......" + (new Date().getTime() - startTime));

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion........request = [" + request + "], response = [" + response + "], handler = [" + handler + "], ex = [" + ex + "]");

        Long startTime = (Long) request.getAttribute("startTime");

        System.out.println("afterCompletion spend time......" + (new Date().getTime() - startTime));

        System.out.println("ex is " + ex);

    }
}
