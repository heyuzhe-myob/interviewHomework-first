package com.thoughtworks.codepairing.model;

public interface DiscountStrategy {
    double calculateTotalPrice(double price, int quantity);

    int calculateLoyaltyPoint(double price, int quantity);
}
