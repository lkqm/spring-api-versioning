package com.github.lkqm.spring.api.version;

import java.util.regex.Pattern;

class InnerUtils {

    private final static Pattern VERSION_NUMBER_PATTERN = Pattern.compile("^\\d+(\\.\\d+){0,2}$");

    /**
     * 检查版本匹配是否复合(最大三个版本)
     */
    public static void checkVersionNumber(String version, Object targetMethodOrType) {
        if (!matchVersionNumber(version)) {
            throw new IllegalArgumentException(String.format("Invalid version number: @ApiVersion(\"%s\") at %s", version, targetMethodOrType));
        }
    }

    /**
     * 判断是否满足最大3个版本号的匹配
     */
    public static boolean matchVersionNumber(String version) {
        return version.length() != 0 && VERSION_NUMBER_PATTERN.matcher(version).find();
    }
}
