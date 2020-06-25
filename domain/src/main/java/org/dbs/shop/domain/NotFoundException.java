package org.dbs.shop.domain;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(BusinessException.NOT_FOUND, message);
    }
}
