package com.tescobank.vending.machine;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: steven
 * Date: 16/01/15
 * Time: 10:56
 */
public class VendingMachine {

    private static final List<BigDecimal> ACCEPTED_COINS = setAcceptedCoins();
    private static final double ZERO_PRICE = 0.00;

    private boolean poweredOn;
    private Map<String, Product> products;
    private BigDecimal insertedCoins;


    public VendingMachine() {
        products = initialiseProducts();
        insertedCoins = MoneyUtils.newPrice(ZERO_PRICE);
    }

    public boolean isPoweredOn() {
        return poweredOn;
    }

    public void togglePowerButton() {
        this.poweredOn = !poweredOn;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public boolean insertCoin(final double coin) {
        BigDecimal insertedCoin = MoneyUtils.newPrice(coin);

        if(ACCEPTED_COINS.contains(insertedCoin)) {
            insertedCoins = insertedCoins.add(insertedCoin);
            return true;
        }

        return false;
    }

    public BigDecimal getInsertedCoins() {
        return insertedCoins;
    }

    public boolean selectProduct(String productCode) {
        Product selectedProduct = products.get(productCode);

        if(selectedProduct != null
                && selectedProduct.hasStock()
                    && MoneyUtils.hasEnoughMoney(insertedCoins, selectedProduct.getPrice())) {

            insertedCoins = insertedCoins.subtract(selectedProduct.getPrice());
            selectedProduct.setCount(selectedProduct.getCount() - 1);
            products.put(productCode, selectedProduct);

            return true;
        }

        return false;
    }

    public BigDecimal coinReturn() {
        BigDecimal change = insertedCoins;
        insertedCoins = MoneyUtils.newPrice(ZERO_PRICE);
        return change;
    }


    private Map<String, Product> initialiseProducts() {
        Map<String, Product> setupProducts = new HashMap<String, Product>();
        setupProducts.put("A", new Product("Mars Bar", 0.60, 3));
        setupProducts.put("B", new Product("Snickers", 1.00, 2));
        setupProducts.put("C", new Product("Twix", 1.70, 1));
        return setupProducts;
    }

    private static List<BigDecimal> setAcceptedCoins() {

        List<BigDecimal> acceptedCoins = new ArrayList<BigDecimal>();
        acceptedCoins.add(MoneyUtils.newPrice(0.10));
        acceptedCoins.add(MoneyUtils.newPrice(0.20));
        acceptedCoins.add(MoneyUtils.newPrice(0.50));
        acceptedCoins.add(MoneyUtils.newPrice(1.00));

        return acceptedCoins;
    }
}
