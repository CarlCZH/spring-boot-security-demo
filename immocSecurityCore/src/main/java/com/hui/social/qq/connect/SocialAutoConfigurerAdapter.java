package com.hui.social.qq.connect;

import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 23:10 2019\2\17 0017
 */
public abstract class SocialAutoConfigurerAdapter extends SocialConfigurerAdapter {

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource() {
            public String getUserId() {
                return "anonymous";
            }
        };
    }

    protected abstract ConnectionFactory<?> createConnectionFactory();

}
