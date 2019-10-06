package com.list.shaddock.common.exception;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6180574613293668909L;

	private BindingResult result;
	
	private Map<String,String> resultMap = new HashMap<String,String>();
	
	public ValidationException(BindingResult result) {
	}

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public void setResult(BindingResult result) {
		this.result = result;
	}

	public BindingResult getResult() {
		return result;
	}

}
