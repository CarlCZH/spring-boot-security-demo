package com.hui.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 15:29 2019\2\8 0008
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService{

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录的用户名：",username);
        return buildUser(username);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录的用户名：",userId);
        return buildUser(userId);
    }

    /**
     * 登录处理
     * @param userId
     * @return
     */
    private SocialUserDetails buildUser(String userId) {
        if (StringUtils.isNotBlank(userId)){
            String password = passwordEncoder.encode("admin");
            logger.info("加密后的password....." + password);
            SocialUser user = new SocialUser(userId, password,
                    true, true, true,true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
            return user;
        }
        return null;
    }
}
