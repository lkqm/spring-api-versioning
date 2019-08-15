package com.github.lkqm.spring.api.version.config;

import com.github.lkqm.spring.api.version.ApiVersionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@EnableConfigurationProperties(ApiVersionProperties.class)
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ApiVersionProperties properties;

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new VersionedRequestMappingHandlerMapping(properties);
    }

}