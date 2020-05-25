package com.github.lkqm.spring.api.version;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启多版本API控制
 *
 * @see ApiVersionProperties 配置属性
 * @see ApiVersionAutoConfiguration 配置类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApiVersionAutoConfiguration.class)
public @interface EnableApiVersioning {
}
