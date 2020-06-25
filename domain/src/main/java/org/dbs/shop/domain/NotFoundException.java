package org.dbs.shop.domain;

public class NotFoundException extends BusinessException {

	public NotFoundException(final String code, final String message) {
		super(code, message);
	}

}
