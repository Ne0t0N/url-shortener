package de.happycar.tryout.util;

import de.happycar.tryout.bean.type.UTM;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public class LinkUtilsTest {

    @Test
    public void isValid_shouldPassWhenValid() {
        Assert.assertTrue(LinkUtils.isValid("http://google.com"));
    }

    @Test
    public void isValid_shouldPassWhenValidWithParams() {
        Assert.assertTrue(LinkUtils.isValid("http://google.com?a=b&c=d"));
    }

    @Test
    public void isValid_shouldFailWhenInvalid() {
        Assert.assertFalse(LinkUtils.isValid("http://google\\sd"));
    }

    @Test
    public void hasParams_shouldPassWhenParamsExist() {
        String link = "http://google.com?" + UTM.UTM_SOURCE.getName() + "=source";
        Assert.assertTrue(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void hasParams_shouldFailWhenParamValuesEmpty() {
        String link = "http://google.com?" + UTM.UTM_SOURCE.getName() + "=";
        Assert.assertFalse(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void hasParams_shouldFailWhenQueryIncorrect() {
        String link = "http://google.com?" + UTM.UTM_SOURCE.getName();
        Assert.assertFalse(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void hasParams_shouldFailWhenLinkHasNoParams() {
        String link = "http://google.com";
        Assert.assertFalse(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void createURL_shouldCreateValidUrl() {
        String expectedUrl = "http://" + PropertyManager.getHost() + ":" + PropertyManager.getPort() + "/path";
        String actualUrl = LinkUtils.createURL("/path");
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
