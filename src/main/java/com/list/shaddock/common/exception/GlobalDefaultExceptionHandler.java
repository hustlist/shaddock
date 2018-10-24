package com.list.shaddock.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.list.shaddock.common.vo.ResultData;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public ResultData<String> defaultErrorHandler(HttpServletRequest req,Exception e) {
		ResultData<String> ret = new ResultData<String>();
		ret.setCode(ResultData.SERVER_ERROR);
		ret.setMsg(e.getMessage());
		return ret;
	}
	
	@ExceptionHandler(value=BusiException.class)
	@ResponseBody
	public ResultData<String> BusiExceptionHandler(HttpServletRequest req,BusiException e){
		ResultData<String> ret = new ResultData<String>();
		ret.setCode(e.getExCode());
		ret.setMsg(e.getExMsg());
		return ret;
	}
	
}
