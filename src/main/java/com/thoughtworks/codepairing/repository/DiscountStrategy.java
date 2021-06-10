package com.thoughtworks.codepairing.repository;

public interface DiscountStrategy {
    double calculateTotalPrice(double price, int quantity);

    int calculateLoyaltyPoint(double price, int quantity);
}
