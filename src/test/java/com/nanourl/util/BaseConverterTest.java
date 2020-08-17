package com.nanourl.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BaseConverterTest {

    private BaseConverter baseConverter = new BaseConverter();

    @Test
    public void encode1() {
        Assert.assertEquals("0", baseConverter.numToUrl(0));
    }

    @Test
    public void encode2() {
        Assert.assertEquals("1", baseConverter.numToUrl(1));
    }

    @Test
    public void encode3() {
        Assert.assertEquals("9", baseConverter.numToUrl(9));
    }

    @Test
    public void encode4() {
        Assert.assertEquals("A", baseConverter.numToUrl(10));
    }

    @Test
    public void encode5() {
        Assert.assertEquals("z", baseConverter.numToUrl(61));
    }

    @Test
    public void encode6() {
        Assert.assertEquals("20", baseConverter.numToUrl(124));
    }

    @Test
    public void decode() {
        Assert.assertEquals(9457, baseConverter.urlToNum("2SX"));
    }
}
