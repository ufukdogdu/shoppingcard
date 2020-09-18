package com.shopping.etrade.exception;

public class IncompatibleCurrencyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3756041942217759929L;

	public IncompatibleCurrencyException() {
		super(ExceptionMessages.INCOM_CURR);
	}
}
