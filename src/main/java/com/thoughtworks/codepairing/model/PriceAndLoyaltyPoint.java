package com.thoughtworks.codepairing.model;

public class PriceAndLoyaltyPoint {
    private DiscountStrategy discountStrategy;

    public PriceAndLoyaltyPoint(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double realPrice(double tagPrice, int quantity) {
        return this.discountStrategy.calculateTotalPrice(tagPrice, quantity);
    }

    public int loyaltyPoint(double price, int quantity) {
        return this.discountStrategy.calculateLoyaltyPoint(price, quantity);
    }
}
