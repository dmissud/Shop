package org.dbs.shop.domain.common;

public class AllReadyExistException extends BusinessException {
    public AllReadyExistException(String message) {
        super(message, BusinessException.ALL_READY_EXIST);
    }
}
