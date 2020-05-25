package com.github.lkqm.spring.api.version;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 支持多版本api的WebMvcConfigurationSupport
 */
@AllArgsConstructor
public abstract class ApiVersionWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    public abstract ApiVersionConfig apiVersionConfig();

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new VersionedRequestMappingHandlerMapping(apiVersionConfig());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
