package com.github.lkqm.spring.api.version;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

@Getter
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    private String apiVersion;
    private ApiVersionConfig apiVersionConfig;

    public ApiVersionRequestCondition(@NonNull String apiVersion, @NonNull ApiVersionConfig apiVersionConfig) {
        this.apiVersion = apiVersion.trim();
        this.apiVersionConfig = apiVersionConfig;
    }

    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {
        // method annotation first
        return new ApiVersionRequestCondition(other.getApiVersion(), other.getApiVersionConfig());
    }

    @Override
    public int compareTo(ApiVersionRequestCondition other, HttpServletRequest request) {
        return other.getApiVersion().compareTo(getApiVersion());
    }

    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        ApiVersionConfig.Type type = apiVersionConfig.getType();
        String version = null;
        switch (type) {
            case HEADER:
                version = request.getHeader(apiVersionConfig.getHeader());
                break;
            case PARAM:
                version = request.getParameter(apiVersionConfig.getParam());
                break;
        }
        boolean match = version != null && version.length() > 0 && version.trim().equals(apiVersion);
        if (match) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "@ApiVersion(" + apiVersion + ")";
    }
}