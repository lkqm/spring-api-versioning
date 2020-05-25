package com.github.lkqm.spring.api.version;

import lombok.Data;

import java.io.Serializable;

/**
 * Api-Version配置
 */
@Data
public class ApiVersionConfig implements Serializable {

    /**
     * 实现多版本的方式
     */
    private Type type = Type.URI;

    /**
     * URI地址前缀, 例如: /api
     */
    private String uriPrefix;

    /**
     * 版本请求头名
     */
    private String header = "X-API-VERSION";

    /**
     * 版本请求参数名
     */
    private String param = "api_version";

    public enum Type {
        /**
         * URI路径
         */
        URI,
        /**
         * 请求头
         */
        HEADER,
        /**
         * 请求参数
         */
        PARAM;
    }
}
