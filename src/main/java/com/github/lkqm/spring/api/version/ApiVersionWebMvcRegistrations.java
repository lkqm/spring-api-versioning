package com.github.lkqm.spring.api.version;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@AllArgsConstructor
public class ApiVersionWebMvcRegistrations implements WebMvcRegistrations {

    @NonNull
    private ApiVersionProperties apiVersionProperties;

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new VersionedRequestMappingHandlerMapping(apiVersionProperties);
    }

}
