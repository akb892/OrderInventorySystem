package com.cg.ims.exception;

public class OrderAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderAlreadyExistsException(String msg) {
		super(msg);
	}
}
