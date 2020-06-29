package org.dbs.shop.domain;

import java.math.BigDecimal;

public class Item {
    public static final float DISCOUNT_POURCENTAGE = 0.9f;
    private final String productName;
    private final BigDecimal productPrice;
    private final int quantity;

    public Item(String productName, BigDecimal productPrice, int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getCost() {
        if (quantity < 10) {
            return this.productPrice.multiply((new BigDecimal(this.quantity)));
        } else {
            return this.productPrice.multiply(BigDecimal.valueOf(DISCOUNT_POURCENTAGE)).multiply((new BigDecimal(this.quantity)));
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                '}';
    }

}
