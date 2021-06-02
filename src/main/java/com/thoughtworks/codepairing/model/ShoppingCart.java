package com.thoughtworks.codepairing.model;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart {
    private List<Product> products;
    private Customer customer;

    public ShoppingCart(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Order checkout() {
        double totalPrice = 0;
        int loyaltyPointsEarned = 0;

        for (Product product : products) {
            double discount = 0;
            if (product.getProductCode().startsWith("BULK_BUY_2_GET_1")) {
                if (product.getQuantity() > 2) {
                    discount = product.getQuantity() / 3 * product.getPrice();
                }
            }
            if (product.getProductCode().startsWith("DIS_10")) {
                discount = (product.getPrice() * product.getQuantity() * 0.1);
                loyaltyPointsEarned += (product.getPrice() * product.getQuantity() / 10);
            } else if (product.getProductCode().startsWith("DIS_15")) {
                discount = (product.getPrice() * product.getQuantity() * 0.15);
                loyaltyPointsEarned += (product.getPrice() * product.getQuantity() / 15);
            } else if (product.getProductCode().startsWith("DIS_20")) {
                discount = (product.getPrice() * product.getQuantity() * 0.2);
                loyaltyPointsEarned += (product.getPrice() * product.getQuantity() / 20);
            } else {
                loyaltyPointsEarned += (product.getPrice() * product.getQuantity() / 5);
            }

            totalPrice += product.getPrice() * product.getQuantity() - discount;
        }
        return new Order(getDiscountForTotalPriceOver500(totalPrice), loyaltyPointsEarned);
    }

    public double getDiscountForTotalPriceOver500(double totalPrice) {
        return totalPrice > 500 ? totalPrice * 0.95 : totalPrice;
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.stream().map(p -> "- " + p.getName() + ", " + p.getPrice()).collect(Collectors.joining("\n"));
    }
}
