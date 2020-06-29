package org.dbs.shop.domain;

public class OrderIsEmpty extends BusinessException {
    public OrderIsEmpty() {
        super("Order command is empty", BusinessException.ORDER_IS_EMPTY);
    }
}
