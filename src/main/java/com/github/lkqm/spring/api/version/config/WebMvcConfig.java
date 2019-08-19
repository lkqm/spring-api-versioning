package com.github.lkqm.spring.api.version.config;

import com.github.lkqm.spring.api.version.ApiVersionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@Configuration
@EnableConfigurationProperties(ApiVersionProperties.class)
public class WebMvcConfig extends WebMvcConfigurationSupport implements InitializingBean {

    @Autowired(required = false)
    private ApiVersionProperties properties;

    @Override
    public void afterPropertiesSet() {
        if (properties == null) properties = new ApiVersionProperties();
    }

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new VersionedRequestMappingHandlerMapping(properties);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}