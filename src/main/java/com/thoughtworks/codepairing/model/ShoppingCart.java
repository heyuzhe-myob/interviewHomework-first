package com.thoughtworks.codepairing.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private HashMap<String, Integer> getProductQuantityMap() {
        HashMap<String, Integer> productQuantityMap = new HashMap<>();
        for (Product p : products) {
            if (!productQuantityMap.containsKey(p.getName())) {
                productQuantityMap.put(p.getName(), 1);
            } else {
                int currentQuantity = productQuantityMap.get(p.getName());
                productQuantityMap.put(p.getName(), currentQuantity + 1);
            }
        }
        return productQuantityMap;
    }

    public Order checkout() {
        double totalPrice = 0;
        int loyaltyPointsEarned = 0;
        Map<String, Integer> productQuantityMap = getProductQuantityMap();
        
        for (String productName : productQuantityMap.keySet()){
            int quantity = productQuantityMap.get(productName);
            double discount = 0;
            if (getProductCodeThroughProductName(productName).startsWith("BULK_BUY_2_GET_1")) {
                if (quantity > 2) {
                    discount = quantity / 3 * getPriceThroughProductName(productName);
                }
            }
            if (getProductCodeThroughProductName(productName).startsWith("DIS_10")) {
                discount = (getPriceThroughProductName(productName) * quantity * 0.1);
                loyaltyPointsEarned += (getPriceThroughProductName(productName) * quantity / 10);
            } else if (getProductCodeThroughProductName(productName).startsWith("DIS_15")) {
                discount = (getPriceThroughProductName(productName) * quantity * 0.15);
                loyaltyPointsEarned += (getPriceThroughProductName(productName) * quantity / 15);
            } else if (getProductCodeThroughProductName(productName).startsWith("DIS_20")) {
                discount = (getPriceThroughProductName(productName) * quantity * 0.2);
                loyaltyPointsEarned += (getPriceThroughProductName(productName) * quantity / 20);
            } else {
                loyaltyPointsEarned += (getPriceThroughProductName(productName) * quantity / 5);
            }
            totalPrice += getPriceThroughProductName(productName) * quantity - discount;
        }

        return new Order(getDiscountForTotalPriceOver500(totalPrice), loyaltyPointsEarned);
    }

    public double getPriceThroughProductName(String productName){
        for (Product product : products) {
            if (product.getName().equals(productName)){
                return product.getPrice();
            }
        }
        return 0.0;
    }

    public String getProductCodeThroughProductName(String productName){
        for (Product product : products) {
            if (product.getName().equals(productName)){
                return product.getProductCode();
            }
        }
        return "";
    }
    
    public double getDiscountForTotalPriceOver500(double totalPrice) {
        return totalPrice > 500 ? totalPrice * 0.95 : totalPrice;
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.stream().map(p -> "- " + p.getName() + ", " + p.getPrice()).collect(Collectors.joining("\n"));
    }
}
