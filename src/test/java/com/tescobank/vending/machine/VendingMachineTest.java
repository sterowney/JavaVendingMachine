package com.tescobank.vending.machine;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: steven
 * Date: 16/01/15
 * Time: 10:54
 */
public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setUp() throws Exception {

        vendingMachine = new VendingMachine();
        assertFalse("New vending machine should be turned off", vendingMachine.isPoweredOn());

        vendingMachine.togglePowerButton();
        assertTrue("Vending machine should be turned on", vendingMachine.isPoweredOn());
    }

    @Test
    public void testFeatureTurnOnAndOff() throws Exception {

        vendingMachine.togglePowerButton();
        assertFalse("Vending machine is now turned off again", vendingMachine.isPoweredOn());
    }

    @Test
    public void testFeatureGetProductList() throws Exception {

        Map<String, Product> products = vendingMachine.getProducts();

        assertNotNull("Products should not be null", products);

        assertEquals("Vending Machine has 3 choices", 3, products.size());

        Product aProduct = products.get("A");

        assertEquals("A is a mars bar", "Mars Bar", aProduct.getName());

        assertEquals("Mars bar is 50p", MoneyUtils.newPrice(0.60), aProduct.getPrice());

        assertEquals("There are 3 Mars Bars left", 3, aProduct.getCount());

        Product bProduct = products.get("B");

        assertEquals("Snickers", bProduct.getName());

        assertEquals(MoneyUtils.newPrice(1.00), bProduct.getPrice());

        assertEquals(2, bProduct.getCount());

        Product cProduct = products.get("C");

        assertEquals("Twix", cProduct.getName());

        assertEquals(MoneyUtils.newPrice(1.70), cProduct.getPrice());

        assertEquals(1, cProduct.getCount());
    }

    @Test
    public void testFeatureInsertCoins() throws Exception {

        assertTrue("insert valid coin", vendingMachine.insertCoin(0.20));

        assertEquals("inserted coins value is", MoneyUtils.newPrice(0.20), vendingMachine.getInsertedCoins());

        assertFalse("insert invalid coin", vendingMachine.insertCoin(0.05));

        assertEquals("inserted coins value is", MoneyUtils.newPrice(0.20), vendingMachine.getInsertedCoins());

        assertFalse("another invalid coin", vendingMachine.insertCoin(2.00));

        assertEquals("inserted coins value is", MoneyUtils.newPrice(0.20), vendingMachine.getInsertedCoins());

        assertTrue("another valid coin", vendingMachine.insertCoin(0.50));

        assertEquals("inserted coins value is", MoneyUtils.newPrice(0.70), vendingMachine.getInsertedCoins());
    }

    @Test
    public void testFeatureSelectProduct() throws Exception {

        assertFalse("select a product with no money", vendingMachine.selectProduct("A"));

        vendingMachine.insertCoin(0.20);
        vendingMachine.insertCoin(0.20);
        vendingMachine.insertCoin(0.20);

        assertEquals("Should have enough for product A", MoneyUtils.newPrice(0.60), vendingMachine.getInsertedCoins());

        assertTrue("select a product and should get it", vendingMachine.selectProduct("A"));

        assertEquals("Should have no change", MoneyUtils.newPrice(0.00), vendingMachine.coinReturn());

        Map<String, Product> products = vendingMachine.getProducts();
        Product aProduct = products.get("A");

        assertEquals("Count of product should be less", 2, aProduct.getCount());

        vendingMachine.insertCoin(1.00);
        vendingMachine.insertCoin(1.00);

        assertEquals("Inserted coins is", MoneyUtils.newPrice(2.00), vendingMachine.getInsertedCoins());

        assertTrue("select a product and should get it", vendingMachine.selectProduct("C"));

        assertEquals("Should have change of", MoneyUtils.newPrice(0.30), vendingMachine.coinReturn());
        assertEquals("Now we've got the change, inserted coins should be", MoneyUtils.newPrice(0.00), vendingMachine.getInsertedCoins());

        products = vendingMachine.getProducts();
        Product cProduct = products.get("C");

        assertEquals("Bought the last one", 0, cProduct.getCount());

        vendingMachine.insertCoin(1.00);
        vendingMachine.insertCoin(1.00);

        assertFalse("Out of stock for product selection", vendingMachine.selectProduct("C"));

        assertFalse("Select a product that doesn't exist", vendingMachine.selectProduct("D"));

        assertEquals("Change should be ", MoneyUtils.newPrice(2.00), vendingMachine.coinReturn());
        assertEquals("Inserted coins is ", MoneyUtils.newPrice(0.00), vendingMachine.getInsertedCoins());
    }
}
