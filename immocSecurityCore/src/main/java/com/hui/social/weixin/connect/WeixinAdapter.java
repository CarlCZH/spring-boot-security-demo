package com.hui.social.weixin.connect;

import com.hui.social.weixin.api.Weixin;
import com.hui.social.weixin.api.WeixinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author: CarlChen
 * @Despriction: WeixinAdapter将微信api返回的数据模型适配Spring Social的标准模型
 * @Date: Create in 22:00 2019\3\24 0024
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {

    private String openId;

    public WeixinAdapter() {
    }

    public WeixinAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(Weixin api) {
        return true;
    }

    @Override
    public void setConnectionValues(Weixin api, ConnectionValues values) {
        WeixinUserInfo weixinUserInfo = api.getUserInfo(openId);

        //将用户信息放入ConnectionValues中
        values.setDisplayName(weixinUserInfo.getNickname());
        values.setImageUrl(weixinUserInfo.getHeadimgurl());
        values.setProviderUserId(weixinUserInfo.getOpenid());
    }

    @Override
    public UserProfile fetchUserProfile(Weixin api) {
        return null;
    }

    @Override
    public void updateStatus(Weixin api, String message) {

    }
}
