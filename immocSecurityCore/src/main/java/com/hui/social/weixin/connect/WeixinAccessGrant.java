package com.hui.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @Author: CarlChen
 * @Despriction: 处理微信返回的access_token类(添加openid)
 * @Date: Create in 13:34 2019\3\24 0024
 */
public class WeixinAccessGrant extends AccessGrant {

    private String openId;

    public WeixinAccessGrant() {
        super("");
    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
