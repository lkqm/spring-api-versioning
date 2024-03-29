package com.github.lkqm.spring.api.version;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

@Slf4j
@AllArgsConstructor
public class VersionedRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    /**
     * 多版本配置属性
     */
    private ApiVersionProperties apiVersionProperties;

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return createRequestCondition(handlerType);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return createRequestCondition(method);
    }

    private RequestCondition<ApiVersionRequestCondition> createRequestCondition(AnnotatedElement target) {
        if (apiVersionProperties.getType() == ApiVersionProperties.Type.URI) {
            return null;
        }
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(target, ApiVersion.class);
        if (apiVersion == null) {
            return null;
        }
        String version = apiVersion.value().trim();
        InnerUtils.checkVersionNumber(version, target);
        return new ApiVersionRequestCondition(version, apiVersionProperties);
    }

    //--------------------- 动态注册URI -----------------------//
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = this.createRequestMappingInfo(method);
        if (info != null) {
            RequestMappingInfo typeInfo = this.createRequestMappingInfo(handlerType);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }

            // 指定URL前缀
            if (apiVersionProperties.getType() == ApiVersionProperties.Type.URI) {
                ApiVersion apiVersion = AnnotationUtils.getAnnotation(method, ApiVersion.class);
                if (apiVersion == null) {
                    apiVersion = AnnotationUtils.getAnnotation(handlerType, ApiVersion.class);
                }
                if (apiVersion != null) {
                    String version = apiVersion.value().trim();
                    InnerUtils.checkVersionNumber(version, method);

                    String prefix = "/v" + version;
                    if (apiVersionProperties.getUriLocation() == ApiVersionProperties.UriLocation.END) {
                        info = info.combine(RequestMappingInfo.paths(prefix).options(getBuilderConfiguration()).build());
                    } else {
                        if (StringUtils.hasText(apiVersionProperties.getUriPrefix())) {
                            prefix = apiVersionProperties.getUriPrefix().trim() + prefix;
                        }
                        info = RequestMappingInfo.paths(prefix).options(getBuilderConfiguration()).build().combine(info);
                    }
                }
            }
        }

        return info;
    }

    private RequestMappingInfo createRequestMappingInfo(AnnotatedElement element) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        RequestCondition<?> condition = (element instanceof Class ?
                getCustomTypeCondition((Class<?>) element) : getCustomMethodCondition((Method) element));
        return (requestMapping != null ? createRequestMappingInfo(requestMapping, condition) : null);
    }

}