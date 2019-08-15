package com.github.lkqm.spring.api.version;

import com.github.lkqm.spring.api.version.config.WebMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置api版本
 */
@Configuration
@Import(WebMvcConfig.class)
public class ApiVersionConfiguration { }
