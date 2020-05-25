package com.github.lkqm.spring.api.version;

import org.junit.Assert;
import org.junit.Test;

public class InnerUtilsTest {

    @Test
    public void matchVersionNumber() {
        Assert.assertTrue(InnerUtils.matchVersionNumber("1"));
        Assert.assertTrue(InnerUtils.matchVersionNumber("1.1"));
        Assert.assertTrue(InnerUtils.matchVersionNumber("1.1.1"));

        Assert.assertFalse(InnerUtils.matchVersionNumber("1.1.1.0"));
        Assert.assertFalse(InnerUtils.matchVersionNumber(""));
        Assert.assertFalse(InnerUtils.matchVersionNumber("."));
        Assert.assertFalse(InnerUtils.matchVersionNumber("a.b"));
    }
}