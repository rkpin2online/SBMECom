package com.rk.exception;

public class CurrencyNotValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2930564511858227609L;

	public CurrencyNotValidException(String message) {
		super(message);
	}
}
