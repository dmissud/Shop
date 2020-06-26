package org.dbs.shop.common;

import org.dbs.shop.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> notFoundExceptionHandler(final NotFoundException ex) {

		final ErrorDTO error = new ErrorDTO();
		error.setMessage(ex.getMessage());
		error.setCode(ex.getCode());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultHandler(final Exception ex) {

		final ErrorDTO error = new ErrorDTO();
		error.setMessage(ex.getMessage());
		error.setCode("99999");

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
