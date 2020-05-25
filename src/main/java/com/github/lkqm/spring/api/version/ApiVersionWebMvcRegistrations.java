package com.github.lkqm.spring.api.version;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@AllArgsConstructor
public class ApiVersionWebMvcRegistrations extends WebMvcRegistrationsAdapter {

    @NonNull
    private ApiVersionProperties apiVersionProperties;

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new VersionedRequestMappingHandlerMapping(apiVersionProperties);
    }

}
