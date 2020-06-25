package org.dbs.shop.domain;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message, BusinessException.NOT_FOUND);
    }
}
