package de.happycar.tryout.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public class IdUtilsTest {

    @Test
    public void generateId_shouldBeSixCharsLength() {
        Assert.assertEquals(6, IdUtils.generateId().length());
    }
}
