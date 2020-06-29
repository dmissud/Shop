package org.dbs.shop.infratructure.order;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class ItemEntity {
    private String productName;
    private BigDecimal productPrice;
    private int quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
