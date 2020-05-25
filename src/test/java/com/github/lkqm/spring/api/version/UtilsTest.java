package com.github.lkqm.spring.api.version;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void matchVersionNumber() {
        Assert.assertTrue(Utils.matchVersionNumber("1"));
        Assert.assertTrue(Utils.matchVersionNumber("1.1"));
        Assert.assertTrue(Utils.matchVersionNumber("1.1.1"));

        Assert.assertFalse(Utils.matchVersionNumber("1.1.1.0"));
        Assert.assertFalse(Utils.matchVersionNumber(""));
        Assert.assertFalse(Utils.matchVersionNumber("."));
        Assert.assertFalse(Utils.matchVersionNumber("a.b"));
    }
}