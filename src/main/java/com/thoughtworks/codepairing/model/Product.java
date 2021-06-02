package com.thoughtworks.codepairing.model;

public class Product {
    private final double price;
    private final String productCode;
    private final String name;
    private final int quantity;

    public Product(double price, String productCode, String name, int quantity) {
        this.price = price;
        this.productCode = productCode;
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }
}
