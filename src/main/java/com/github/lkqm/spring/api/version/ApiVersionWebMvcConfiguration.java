package com.github.lkqm.spring.api.version;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 支持多版本api的WebMvcConfigurationSupport
 */
@Configuration
@AllArgsConstructor
public class ApiVersionWebMvcConfiguration extends WebMvcConfigurationSupport {

    @NonNull
    private ApiVersionConfig apiVersionConfig;

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new VersionedRequestMappingHandlerMapping(apiVersionConfig);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
