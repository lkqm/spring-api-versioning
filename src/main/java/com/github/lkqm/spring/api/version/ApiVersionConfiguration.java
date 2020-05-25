package com.github.lkqm.spring.api.version;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置api版本
 */
@Configuration
public class ApiVersionConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("api.version")
    public ApiVersionConfig apiVersionConfig() {
        return new ApiVersionConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApiVersionWebMvcConfiguration apiVersionWebMvcConfiguration(ApiVersionConfig apiVersionConfig) {
        return new ApiVersionWebMvcConfiguration(apiVersionConfig);
    }
}
