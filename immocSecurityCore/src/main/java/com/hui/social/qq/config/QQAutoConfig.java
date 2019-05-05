package com.hui.social.qq.config;

import com.hui.properties.QQproperties;
import com.hui.properties.SecurityProperties;
import com.hui.social.qq.api.QQ;
import com.hui.social.qq.connect.QQConnectionFactory;
import com.hui.social.qq.connect.SocialAutoConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author: CarlChen
 * @Despriction: QQ自动配置类
 * @Date: Create in 22:08 2019\2\17 0017
 */
@Configuration
@ConditionalOnProperty(prefix = "com.hui.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<QQ> createConnectionFactory() {
        QQproperties qQproperties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qQproperties.getProviderId(), qQproperties.getAppId(), qQproperties.getAppSecret());
    }
}
