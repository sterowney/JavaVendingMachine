package com.tescobank.vending.machine;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: steven
 * Date: 16/01/15
 * Time: 11:16
 */
public class Product {

    private final String name;
    private final BigDecimal price;
    private int count;

    public Product(final String productName, final double productPrice, int count) {
        this.name = productName;
        this.price = MoneyUtils.newPrice(productPrice);
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int newCount) {
        this.count = newCount;
    }

    public boolean hasStock() {
        return this.count > 0 ? true : false;
    }
}
