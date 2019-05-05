package com.hui.social.weixin.connect;

import com.hui.social.weixin.api.Weixin;
import com.hui.social.weixin.api.WeixinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author: CarlChen
 * @Despriction: WeixinServiceProvider连接服务提供商
 * @Date: Create in 21:47 2019\3\24 0024
 */
public class WeixinServiceProvoder extends AbstractOAuth2ServiceProvider<Weixin> {

    /**
     * 微信获取授权码的url
     */
    private static final String WEIXIN_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 微信获取accessToken的url(微信在获取accessToken时也已经返回openId)
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * Create a new {@link}
     */
    public WeixinServiceProvoder(String appId, String appSecret) {
        super(new WeixinOAuth2Template(appId, appSecret, WEIXIN_AUTHORIZE_URL, ACCESS_TOKEN_URL));
    }

    @Override
    public Weixin getApi(String accessToken) {
        return new WeixinImpl(accessToken);
    }
}
