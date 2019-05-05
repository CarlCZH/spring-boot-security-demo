package com.hui.social.qq.connect;

import com.hui.social.qq.api.QQ;
import com.hui.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author: CarlChen
 * @Despriction: ServiceProvider服务提供商
 * @Date: Create in 21:06 2019\2\17 0017
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

    private String appId;

    //获取Authorization Code
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    //通过Authorization Code获取Access Token
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
