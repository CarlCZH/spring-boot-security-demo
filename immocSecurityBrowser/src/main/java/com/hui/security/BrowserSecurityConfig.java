package com.hui.security;

import com.hui.properties.SecurityProperties;
import com.hui.validateCode.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Author: CarlChen
 * @Despriction: 登录security的配置
 * @Date: Create in 15:30 2019\2\8 0008
 */
@Component
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private SpringSocialConfigurer huiSpringSocialConfigure;
    /**
     * 密码加密,解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .apply(huiSpringSocialConfigure)
                .and()
                .formLogin()
                    .loginPage("/authentication/index")
                    .loginProcessingUrl("/authentication/login")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                    .antMatchers("/authentication/index",
                        securityProperties.getBrowser().getLoginPage(),
                        "/image/code", "/auth/qq", "/auth/weixin").permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .csrf()
                    .disable();
    }
}
