package com.hui.security;

import com.hui.properties.BrowserProperties;
import com.hui.properties.SecurityProperties;
import com.hui.security.supportBean.SimpleJsonMsg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 16:52 2019\2\8 0008
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 请求session中的缓存
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 目标URL重定向
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
//    @Autowired
//    private BrowserProperties browserProperties;

    /**
     * 当需要身份认证时,跳转这里
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/authentication/index")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleJsonMsg requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从请求session获取请求信息
        SavedRequest saveRequest = requestCache.getRequest(request, response);
        if (saveRequest != null){
            String redirectUrl = saveRequest.getRedirectUrl();
            logger.info("请求URL....." + redirectUrl);
            String loginPageUrl = securityProperties.getBrowser().getLoginPage();
            if (!redirectUrl.equals(loginPageUrl)){
                redirectStrategy.sendRedirect(request, response, loginPageUrl);
            }else if("/authentication/index".equals(redirectUrl)){
                redirectStrategy.sendRedirect(request, response, loginPageUrl);
            }
        }
        return new SimpleJsonMsg("需要认证,请去往首页认证.......");
    }

}
