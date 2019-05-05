package com.hui.social.weixin.config;

import com.hui.properties.SecurityProperties;
import com.hui.properties.SocialPropertiesD;
import com.hui.social.qq.connect.SocialAutoConfigurerAdapter;
import com.hui.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author: CarlChen
 * @Despriction: 微信登录配置
 * @Date: Create in 22:38 2019\3\24 0024
 */
@Configuration
@ConditionalOnProperty(prefix = "com.hui.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        SocialPropertiesD configureSocial = securityProperties.getSocial();
        WeixinConnectionFactory weixinConnectionFactory = new WeixinConnectionFactory(
                configureSocial.getWeixin().getProviderId(),
                configureSocial.getWeixin().getAppId(),
                configureSocial.getWeixin().getAppSecret());
        return weixinConnectionFactory;
    }



}
