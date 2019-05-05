package com.hui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: CarlChen
 * @Despriction: 主启动类
 * @Date: Create in 0:58 2019\2\1 0001
 */

@RestController
//去除security的登录验证
//@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping(value = "/hello")
    public String sayHello(){
        return "HAHAHA SAY HELLO!";
    }

}
