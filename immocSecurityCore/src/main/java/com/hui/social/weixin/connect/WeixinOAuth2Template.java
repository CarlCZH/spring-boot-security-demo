package com.hui.social.weixin.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @Author: CarlChen
 * @Despriction: 自定义WeixinOAuth2Template处理微信返回的令牌信息
 * @Date: Create in 14:23 2019\3\24 0024
 */
public class WeixinOAuth2Template extends OAuth2Template {

    private static final Logger logger = LoggerFactory.getLogger(WeixinOAuth2Template.class);

    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String WEIXIN_REFERSH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public WeixinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
                                         MultiValueMap<String, String> additionalParameters) {
        StringBuffer accessTokenRequestUrl = new StringBuffer(accessTokenUrl);
        accessTokenRequestUrl.append("?appid=" + clientId);
        accessTokenRequestUrl.append("&secret=" + clientSecret);
        accessTokenRequestUrl.append("&code=" + authorizationCode);
        accessTokenRequestUrl.append("&grant_type=authorization_code");
        accessTokenRequestUrl.append("&redirect_uri=" + redirectUri);

        return getAccessToken(accessTokenRequestUrl.toString());

    }


    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuffer refershTokenRequestUrl = new StringBuffer(WEIXIN_REFERSH_TOKEN_URL);
        refershTokenRequestUrl.append("?appid=APPID" + clientId);
        refershTokenRequestUrl.append("&grant_type=" + refreshToken);
        refershTokenRequestUrl.append("&refresh_token=REFRESH_TOKEN");

        return getAccessToken(refershTokenRequestUrl.toString());

    }

    /**
     * 解析获取回来的参数,并封装到access_token类中
     * @param requestUrl
     * @return
     */
    private AccessGrant getAccessToken(String requestUrl) {
        logger.info("获取access_token, 请求URL：" + requestUrl);

        String response = getRestTemplate().getForObject(requestUrl, String.class);

        logger.info("获取access_token, 响应内容: " + response);

        Map<String, Object> resultMap = null;

        try {
            resultMap = objectMapper.readValue(response, Map.class);
        }catch (Exception e){
            logger.error("解析响应内容失败", e);
        }

        //返回错误码时直接返回空
        if (StringUtils.isNotBlank(MapUtils.getString(resultMap, "errcode"))){
            String errcode = MapUtils.getString(resultMap, "errcode");
            String errmsg = MapUtils.getString(resultMap, "errmsg");
            throw new RuntimeException("获取access token失败, errcode:" + errcode + ", errmsg:" + errmsg);
        }

        //解析获取回来的参数,并封装到access_token类中
        WeixinAccessGrant accessToken = new WeixinAccessGrant(
                MapUtils.getString(resultMap, "access_token"),
                MapUtils.getString(resultMap, "scope"),
                MapUtils.getString(resultMap, "refresh_token"),
                Long.valueOf(MapUtils.getString(resultMap, "expires_in"))
        );
        accessToken.setOpenId(MapUtils.getString(resultMap, "openid"));

        return accessToken;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return super.buildAuthenticateUrl(parameters);
    }

    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     */
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&appid="+clientId+"&scope=snsapi_login";
        return url;
    }

    /**
     * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
     * */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
