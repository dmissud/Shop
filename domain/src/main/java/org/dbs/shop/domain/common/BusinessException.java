package org.dbs.shop.domain.common;

public abstract class BusinessException extends RuntimeException {
    public static final String NOT_FOUND = "BUSINESS_NOT_FOUND";
    public static final String ALL_READY_EXIST = "BUSINESS_ALL_READY_EXIST";
    protected static final String ORDER_IS_EMPTY = "BUSINESS_ORDER_EMPTY";
    private final String code;

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
