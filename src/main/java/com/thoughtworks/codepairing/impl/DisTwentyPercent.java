package com.thoughtworks.codepairing.impl;

import com.thoughtworks.codepairing.repository.DiscountStrategy;

public class DisTwentyPercent implements DiscountStrategy {
    @Override
    public double calculateTotalPrice(double price, int quantity) {
        return price * quantity * 0.8;
    }

    @Override
    public int calculateLoyaltyPoint(double price, int quantity) {
        return (int) price * quantity / 20;
    }
}
