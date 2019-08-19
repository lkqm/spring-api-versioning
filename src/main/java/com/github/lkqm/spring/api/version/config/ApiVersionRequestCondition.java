package com.github.lkqm.spring.api.version.config;

import com.github.lkqm.spring.api.version.ApiVersionProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    /**
     * 接口路径中的版本号前缀，如: api/v[1-n]/test
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v(\\d+)/");

    private int apiVersion;
    private ApiVersionProperties apiVersionProperties;

    public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {
        // 方法级别注解优先
        return new ApiVersionRequestCondition(other.getApiVersion(), other.getApiVersionProperties());
    }

    public int compareTo(ApiVersionRequestCondition other, HttpServletRequest request) {
        return other.getApiVersion() - getApiVersion();
    }

    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        ApiVersionProperties.Type type = apiVersionProperties.getType();
        String version = null;
        switch (type) {
            case URI:
                Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
                if (m.find()) version = m.group(1);
                break;
            case HEADER:
                version = request.getHeader(apiVersionProperties.getHeader());
                break;
            case PARAM:
                version = request.getParameter(apiVersionProperties.getParam());
                break;
        }
        if (StringUtils.isEmpty(version)) return null;

        try {
            Integer intVersion = Integer.valueOf(version);
            if (intVersion == getApiVersion()) return this;
        } catch (NumberFormatException e) {
        }
        return null;
    }

    @Override
    public String toString() {
        return "@ApiVersion(" + apiVersion + ")";
    }
}