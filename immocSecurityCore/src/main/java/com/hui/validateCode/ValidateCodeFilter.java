package com.hui.validateCode;

import com.hui.contanst.CommonContsnast;
import com.hui.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: CarlChen
 * @Despriction: 加入自定义的验证过滤器
 *  OncePerRequestFilter是springSecurity的过滤器,是用来保证只调用一次请求
 * @Date: Create in 19:29 2019\2\10 0010
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    /**
     * 认证错误处理器
     */
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * spring中操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 放入需要验证的URL
     */
    private static Set<String> urls = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] urlArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidateCode().getImageCodeValidate().getUrls(),
                ",");
        //将需要验证的url放入urls集合中
        for (String url : urlArray){
            urls.add(url);
        }
        urls.add("/authentication/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        for (String url : urls){
            //将请求中的url与需要验证urls进行匹配、
            if (antPathMatcher.match(url, request.getRequestURI())){
                flag = true;
            }
        }
        if (flag){
            try {
                //将HttpServletRequest转为ServletWebRequest
                ServletWebRequest servletWebRequest = new ServletWebRequest(request);
                validate(servletWebRequest);
            }catch (ValidateCodeException e){
                //将错误的信息加入到错误认证处理器
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        //成功,继续往下
        filterChain.doFilter(request, response);
    }

    /**
     * 对session和request中验证码进行判断
     * @param servletWebRequest
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        //从session获取图形验证码
        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, CommonContsnast.SESSION_KEY);
        //从rquest请求中获取验证码
        String imageCodeRequst = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if (StringUtils.isBlank(imageCodeRequst)){
            throw new ValidateCodeException("验证码不能为空");
        }
        if (imageCode == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCode.isExpire()){
            //验证码失效,将验证码从session中移除
            sessionStrategy.removeAttribute(servletWebRequest, CommonContsnast.SESSION_KEY);
            throw new ValidateCodeException("验证码已失效");
        }
        if (!imageCodeRequst.equalsIgnoreCase(imageCode.getCode())){
            throw new ValidateCodeException("验证码不匹配");
        }

        //成功，将验证码从session中移除
        sessionStrategy.removeAttribute(servletWebRequest, CommonContsnast.SESSION_KEY);
    }


    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

}
