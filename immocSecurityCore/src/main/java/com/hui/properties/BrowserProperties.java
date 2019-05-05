package com.hui.properties;


import org.springframework.stereotype.Component;

/**
 * @Author: CarlChen
 * @Despriction: PC端配置
 * @Date: Create in 17:23 2019\2\8 0008
 */
@Component
public class BrowserProperties {

    /**
     * 登录页面
     */
    private String loginPage;

    /**
     * 跳转的形式
     */
    private String loginReturnType;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginReturnType() {
        return loginReturnType;
    }

    public void setLoginReturnType(String loginReturnType) {
        this.loginReturnType = loginReturnType;
    }
}
