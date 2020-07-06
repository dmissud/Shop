package org.dbs.shop.domain.common;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message, BusinessException.NOT_FOUND);
    }
}
