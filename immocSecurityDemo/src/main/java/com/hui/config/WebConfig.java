package com.hui.config;

import com.hui.filter.TimeFilter;
import com.hui.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: CarlChen
 * @Despriction: web容器自写配置添加  继承WebMvcConfigure
 * @Date: Create in 16:59 2019\2\7 0007
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将自写拦截器添加
//        registry.addInterceptor(new TimeInterceptor());
    }

//    @Bean
    public FilterRegistrationBean<TimeFilter> timeFilter(){
        FilterRegistrationBean<TimeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        List<String> list = new ArrayList<>();
        list.add("/*");

        //加入过滤器到filterRegistrationBean
        filterRegistrationBean.setFilter(new TimeFilter());
        //添加要过滤的路径
        filterRegistrationBean.setUrlPatterns(list);

        return filterRegistrationBean;
    }


}
