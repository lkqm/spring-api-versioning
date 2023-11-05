package com.github.lkqm.spring.api.version;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;


@Getter
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    private final String apiVersion;
    private final ApiVersionProperties apiVersionProperties;

    public ApiVersionRequestCondition(@NonNull String apiVersion, @NonNull ApiVersionProperties apiVersionProperties) {
        this.apiVersion = apiVersion.trim();
        this.apiVersionProperties = apiVersionProperties;
    }

    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {
        // method annotation first
        return new ApiVersionRequestCondition(other.getApiVersion(), other.getApiVersionProperties());
    }

    @Override
    public int compareTo(ApiVersionRequestCondition other, HttpServletRequest request) {
        return other.getApiVersion().compareTo(getApiVersion());
    }

    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        ApiVersionProperties.Type type = apiVersionProperties.getType();
        String version = null;
        switch (type) {
            case HEADER:
                version = request.getHeader(apiVersionProperties.getHeader());
                break;
            case PARAM:
                version = request.getParameter(apiVersionProperties.getParam());
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