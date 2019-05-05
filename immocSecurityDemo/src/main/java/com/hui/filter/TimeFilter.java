package com.hui.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: CarlChen
 * @Despriction: 时间过滤器 filter的demo
 * @Date: Create in 16:45 2019\2\7 0007
 */
//@Component
public class TimeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filterConfig = [" + filterConfig + "]");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long time = new Date().getTime();
        System.out.println("doFilter begin......");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("doFilter spend time......" + (new Date().getTime() - time));
    }

    @Override
    public void destroy() {
        System.out.println("destroy.......");
    }
}
