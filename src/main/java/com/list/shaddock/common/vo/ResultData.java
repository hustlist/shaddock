package com.list.shaddock.common.vo;

/**
 * @author Administrator
 *
 * @param <T>
 */
public class ResultData <T>{
	
	public static final String SERVER_ERROR = "0001";
	
	
	/**
	 * 返回状态码
	 */
	private String code;
	
	/**
	 * 返回消息码
	 */
	private String msg;
	
	/**
	 * 返回数据类型
	 */
	private T data;
	
	

	/**
	 * 
	 */
	public ResultData() {
		super();
		this.code = "0000";
		this.msg = "请求成功";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
