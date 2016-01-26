package com.thebluecabrio.vending.machine;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: steven
 * Date: 16/01/15
 * Time: 11:32
 */
public class MoneyUtilsTest {

    @Test(expected = InstantiationError.class)
    public void testInstantiateCurrenctUtils() throws Exception {
        new MoneyUtils();
    }

}