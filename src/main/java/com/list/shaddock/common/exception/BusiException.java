package com.list.shaddock.common.exception;

public class BusiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653607347985315866L;

	private String exCode;
	
	private String exMsg;

	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public String getExMsg() {
		return exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}
	
	public BusiException() {
		super();
	}

	public BusiException(String exCode, String exMsg) {
		super();
		this.exCode = exCode;
		this.exMsg = exMsg;
	}
	
	
	
	
}
