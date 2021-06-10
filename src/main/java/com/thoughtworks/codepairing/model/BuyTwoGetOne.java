package com.thoughtworks.codepairing.model;

public class BuyTwoGetOne implements DiscountStrategy {
    @Override
    public double calculateTotalPrice(double price, int quantity) {
        return price * quantity - (quantity / 3 * price);
    }

    @Override
    public int calculateLoyaltyPoint(double price, int quantity) {
        //TODO:No rule set for loyaltyPoint of BuyTwoGet ON
        return 0;
    }
}
