package com.hui.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: CarlChen
 * @Despriction: 实现返回用户信息接口
 * @Date: Create in 13:24 2019\3\24 0024
 */
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

    private static Logger logger = LoggerFactory.getLogger(WeixinImpl.class);

    private static final String WEIXIN_GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    public WeixinImpl(String accessToken){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    @Override
    public WeixinUserInfo getUserInfo(String openId) {
        String url = String.format(WEIXIN_GET_USER_INFO_URL, openId);
        //使用RestTemplte模板发送url获取用户信息
        String result = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(result, "errcode")){
            return null;
        }
        WeixinUserInfo weixinUserInfo = null;
        try {
            weixinUserInfo = objectMapper.readValue(result, WeixinUserInfo.class);
        }catch (Exception e){
            logger.error("获取微信用户信息失败", e);
        }

        return weixinUserInfo;
    }

    /**
     * 将获取微信返回的数据格式iso-8859-1转换为UTF-8
     * @return
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return messageConverters;
    }
}
