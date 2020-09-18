package com.shopping.etrade.exception;

public class SpendingIsNotEnoughException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3174923590439998038L;

	public SpendingIsNotEnoughException() {
		super(ExceptionMessages.SPENDING_IS_NOT_ENOUGH);
	}
}
