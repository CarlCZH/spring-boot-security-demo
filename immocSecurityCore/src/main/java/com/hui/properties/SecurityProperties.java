package com.hui.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Author: CarlChen
 * @Despriction: TODO
 * @Date: Create in 17:22 2019\2\8 0008
 */
@Component
@ConfigurationProperties(prefix = "com.hui")
public class SecurityProperties {

    private BrowserProperties browser;

    private ValidateCodeProperties validateCode;

    private SocialPropertiesD social;

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public SocialPropertiesD getSocial() {
        return social;
    }

    public void setSocial(SocialPropertiesD social) {
        this.social = social;
    }
}
