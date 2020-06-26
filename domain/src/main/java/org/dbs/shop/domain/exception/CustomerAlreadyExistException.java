package org.dbs.shop.domain.exception;

public class CustomerAlreadyExistException extends BusinessException {

	public CustomerAlreadyExistException(final String customerName) {
		super(ALREADY_EXIST_CODE, "The customer allready exist : " + customerName);
	}

}
