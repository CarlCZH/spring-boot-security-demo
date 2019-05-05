package com.hui.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hui.properties.SecurityProperties;
import com.hui.security.supportBean.ReturnFrom;
import com.hui.security.supportBean.SimpleJsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: CarlChen
 * @Despriction: 自定义登录失败处理
 * @Date: Create in 22:05 2019\2\8 0008
 */
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败......");

        if (ReturnFrom.JSONS.toString().equals(securityProperties.getBrowser().getLoginReturnType())){
            //返回形式为JSON
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleJsonMsg(exception.getMessage())));
        }else {
            //返回形式为spring默认，跳转形式
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
