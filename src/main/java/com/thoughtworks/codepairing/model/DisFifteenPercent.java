package com.thoughtworks.codepairing.model;

public class DisFifteenPercent implements DiscountStrategy {
    @Override
    public double calculateTotalPrice(double price, int quantity) {
        return price * quantity * 0.85;
    }

    @Override
    public int calculateLoyaltyPoint(double price, int quantity) {
        return (int) price * quantity / 15;
    }
}
