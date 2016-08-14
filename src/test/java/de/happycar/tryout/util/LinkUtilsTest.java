package de.happycar.tryout.util;

import de.happycar.tryout.bean.type.UTM;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public class LinkUtilsTest {

    @Test
    public void isValid_shouldPassWhenValid() {
        assertTrue(LinkUtils.isValid("http://google.com"));
    }

    @Test
    public void isValid_shouldPassWhenValidWithParams() {
        assertTrue(LinkUtils.isValid("http://google.com?a=b&c=d"));
    }

    @Test
    public void isValid_shouldFailWhenInvalid() {
        assertFalse(LinkUtils.isValid("http://google\\sd"));
    }

    @Test
    public void hasParams_shouldPassWhenParamsExist() {
        String link = "http://google.com?" + UTM.UTM_SOURCE.getName() + "=source";
        assertTrue(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void hasParams_shouldFailWhenParamValuesEmpty() {
        String link = "http://google.com?" + UTM.UTM_SOURCE.getName() + "=";
        assertFalse(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void hasParams_shouldFailWhenQueryIncorrect() {
        String link = "http://google.com?" + UTM.UTM_SOURCE.getName();
        assertFalse(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void hasParams_shouldFailWhenLinkHasNoParams() {
        String link = "http://google.com";
        assertFalse(LinkUtils.hasParams(link, UTM.UTM_SOURCE));
    }

    @Test
    public void createURL_shouldCreateValidUrl() {
        String expectedUrl = "http://" + PropertyManager.getHost() + ":" + PropertyManager.getPort() + "/path";
        String actualUrl = LinkUtils.createURL("/path");
        assertEquals(expectedUrl, actualUrl);
    }
}
