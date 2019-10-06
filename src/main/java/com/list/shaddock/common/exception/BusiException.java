package com.list.shaddock.common.exception;

public class BusiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653607347985315866L;

	private final String exCode;
	
	private final String exMsg;

	public String getExCode() {
		return exCode;
	}

	public String getExMsg() {
		return exMsg;
	}

	public BusiException(String exCode, String exMsg) {
		super();
		this.exCode = exCode;
		this.exMsg = exMsg;
	}
	
	
	
	
}
