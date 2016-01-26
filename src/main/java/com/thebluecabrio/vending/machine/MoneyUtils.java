package com.thebluecabrio.vending.machine;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: steven
 * Date: 16/01/15
 * Time: 11:33
 */
public final class MoneyUtils {

    private static final int SCALE = 2;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
    private static final int THE_SAME = 0;

    protected MoneyUtils() {
        throw new InstantiationError("Utility class, do not instantiate");
    }

    public static BigDecimal newPrice(double price) {
        return new BigDecimal(price).setScale(SCALE, ROUNDING_MODE);
    }

    public static boolean hasEnoughMoney(BigDecimal currentMoney, BigDecimal price) {
        if(currentMoney.compareTo(price) >= THE_SAME) {
            return true;
        }
        return false;
    }
}
