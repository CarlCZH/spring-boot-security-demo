package com.hui;

import com.hui.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 17:30 2019\2\8 0008
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreProperties {
}
