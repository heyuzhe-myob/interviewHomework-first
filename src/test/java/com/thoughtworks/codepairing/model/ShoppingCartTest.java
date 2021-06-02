package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final String PRODUCT = "Product";
    public static final String EXTRAPRODUCT="extraProduct";

    Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("test");
    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "", PRODUCT, 2));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(200.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "", PRODUCT, 2));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(40, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "DIS_10_ABCD", PRODUCT, 3));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(270.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "DIS_10_ABCD", PRODUCT, 3));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(30, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "DIS_15_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(85.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "DIS_15_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(6, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor20PercentDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "DIS_20_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(80.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor20PercentDiscount() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "DIS_20_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(5, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceWhenBulkBuyWithBuyingTwoAndGetThree() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "BULK_BUY_2_GET_1", PRODUCT, 5));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(400.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceWhenBuyTwoForBuyingTwoAndGetThree() {
        List<Product> products = Collections.singletonList(new Product(PRICE, "BULK_BUY_2_GET_1", PRODUCT, 2));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(200.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForTwoProductWithNoDiscountOneProductWith10Discount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT, 2),
                new Product(PRICE, "DIS_10_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(290.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointForTwoProductWithNoDiscountOneProductWith10Discount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT, 2),
                new Product(PRICE, "DIS_10_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(50, order.getLoyaltyPoints(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBulkBuyAndNonBulkBuy() {
        List<Product> products = asList(new Product(PRICE, "BULK_BUY_2_GET_1", PRODUCT, 7), new Product(PRICE, "DIS_10_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(560.5, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldHave5DiscountWhenTotalPriceIsOver500() {
        List<Product> products = asList(new Product(PRICE, "BULK_BUY_2_GET_1", PRODUCT, 7), new Product(PRICE, "DIS_10_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(560.5, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForComplexBuy() {
        List<Product> products = asList(new Product(PRICE, "BULK_BUY_2_GET_1", PRODUCT, 7),
                new Product(PRICE, "BULK_BUY_2_GET_1", EXTRAPRODUCT, 2),
                new Product(PRICE, "DIS_20_ABCD", PRODUCT, 1),
                new Product(PRICE, "DIS_10_ABCD", PRODUCT, 1));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(826.5, order.getTotalPrice(), 0.0);
    }
}
