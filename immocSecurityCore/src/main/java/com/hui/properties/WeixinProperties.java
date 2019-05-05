package com.hui.properties;

/**
 * @Author: CarlChen
 * @Despriction: 微信配置属性
 * @Date: Create in 22:45 2019\3\24 0024
 */
public class WeixinProperties {

    private String appId;

    private String appSecret;

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weixin";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
