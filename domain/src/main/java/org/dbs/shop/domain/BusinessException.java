package org.dbs.shop.domain;

public abstract class BusinessException extends RuntimeException {

	private String code;

	public BusinessException(final String code, final String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

}
