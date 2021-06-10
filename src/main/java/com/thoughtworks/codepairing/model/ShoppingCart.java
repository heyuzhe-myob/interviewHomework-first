package com.thoughtworks.codepairing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart {
    private final List<Product> products;
    private final Customer customer;

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
        Map<String, Integer> productQuantityMap = getProductQuantityMap();

        for (String productName : productQuantityMap.keySet()) {
            String productCode = getProductCodeThroughProductName(productName);
            double tagPrice = getPriceThroughProductName(productName);
            int quantity = productQuantityMap.get(productName);
            totalPrice += getPrice(productCode, tagPrice, quantity);
            loyaltyPointsEarned += getLoyaltyPoint(productCode, tagPrice, quantity);
        }
        return new Order(getDiscountForTotalPriceOver500(totalPrice), loyaltyPointsEarned);
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

    public double getPriceThroughProductName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product.getPrice();
            }
        }
        return 0.0;
    }

    public String getProductCodeThroughProductName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product.getProductCode();
            }
        }
        return "";
    }

    public double getPrice(String productCode, double price, int quantity) {
        return readyToCalculate(productCode).realPrice(price, quantity);
    }

    public double getLoyaltyPoint(String productCode, double price, int quantity) {
        return readyToCalculate(productCode).loyaltyPoint(price, quantity);
    }

    private PriceAndLoyaltyPoint readyToCalculate(String productCode) {
        DiscountStrategy disCountStrategy = StrategyFactory.getStrategy(productCode);
        return new PriceAndLoyaltyPoint(disCountStrategy);
    }

    public double getDiscountForTotalPriceOver500(double totalPrice) {
        return totalPrice > 500 ? totalPrice * 0.95 : totalPrice;
    }



    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.stream().map(p -> "- " + p.getName() + ", " + p.getPrice()).collect(Collectors.joining("\n"));
    }
}
