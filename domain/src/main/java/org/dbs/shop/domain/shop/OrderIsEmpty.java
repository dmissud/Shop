package org.dbs.shop.domain.shop;

import org.dbs.shop.domain.common.BusinessException;

public class OrderIsEmpty extends BusinessException {
    public OrderIsEmpty() {
        super("Order command is empty", BusinessException.ORDER_IS_EMPTY);
    }
}
