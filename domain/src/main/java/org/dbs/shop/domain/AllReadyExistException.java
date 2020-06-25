package org.dbs.shop.domain;

public class AllReadyExistException extends BusinessException {
    public AllReadyExistException(String message) {
        super(message, BusinessException.ALL_READY_EXIST);
    }
}
