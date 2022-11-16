package com.github.lkqm.spring.api.version;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InnerUtilsTest {

    @Test
    public void matchVersionNumber() {
        Assertions.assertTrue(InnerUtils.matchVersionNumber("1"));
        Assertions.assertTrue(InnerUtils.matchVersionNumber("1.1"));
        Assertions.assertTrue(InnerUtils.matchVersionNumber("1.1.1"));

        Assertions.assertFalse(InnerUtils.matchVersionNumber("1.1.1.0"));
        Assertions.assertFalse(InnerUtils.matchVersionNumber(""));
        Assertions.assertFalse(InnerUtils.matchVersionNumber("."));
        Assertions.assertFalse(InnerUtils.matchVersionNumber("a.b"));
    }
}