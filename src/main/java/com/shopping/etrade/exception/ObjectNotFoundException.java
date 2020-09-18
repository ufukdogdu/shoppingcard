package com.shopping.etrade.exception;

public class ObjectNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4021561844513959236L;

	public ObjectNotFoundException() {
		super(ExceptionMessages.OBJ_NOT_FOUND);
	}
}
