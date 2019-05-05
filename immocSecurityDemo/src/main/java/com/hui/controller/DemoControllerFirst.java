package com.hui.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hui.bean.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: CarlChen
 * @Despriction: TODO
 * @Date: Create in 17:10 2019\2\6 0006
 */
@Controller
public class DemoControllerFirst {

    @GetMapping(value = "/me")
    @ResponseBody
    public Authentication securityAuthenticationContext(@AuthenticationPrincipal Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping(value = "/user/{id:\\d+}")
    @ResponseBody
    @JsonView(User.ViewDetailView.class)
    @ApiOperation(value = "根据ID获取用户信息")
    public User getUserById(@ApiParam(value = "用户ID") @PathVariable int id){
        User user = new User();
        user.setName("张三");
        user.setAge(25);
        user.setId(id);
        user.setSex("男");
        user.setPwd("123456789");
        return user;
    }

    @GetMapping(value = "/user")
    @ResponseBody
    @JsonView(User.ViewSimpleView.class)
    @ApiOperation(value = "获取所有用户信息")
    public User getUser(){

//        throw new RuntimeException("this is a test exception");
        User user = new User();
        user.setName("李四");
        user.setAge(25);
        user.setId(2);
        user.setSex("男");
        user.setPwd("123456789");
        return user;
    }

}
