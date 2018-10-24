package com.list.demo.account.controller;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.list.demo.account.model.*;
import com.list.demo.account.service.*;

import com.list.shaddock.common.vo.PageRows;
import com.list.shaddock.common.vo.ResultData;
import com.list.shaddock.common.vo.FormData;
import com.list.shaddock.common.locale.LocalMessageSourceBean;
import com.list.shaddock.common.exception.ValidationException;

/*
 * spring mvc控制类
 */
@Api("Account相关api")
@RestController
@RequestMapping(value = "/demo/account")
public class AccountController {

	//日志对象
	private static final Logger log = LogManager.getLogger(AccountController.class);
	
	//本身服务对象
	@Autowired
	private IAccountService accountService = null;
	
	//多语言对象
	@Autowired
	private LocalMessageSourceBean lms = null;
	
	@ApiOperation("根据逐渐获取实体对象-get方式")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "accountId", dataType = "Long",required=true,value="主键",defaultValue="")
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@GetMapping(value = "/{accountId}")
	@ResponseBody
	public ResultData<Account> get(@PathVariable("accountId") Long accountId ) throws Exception {
		//返回统一的服务端数据对象
		ResultData<Account> resultData = new ResultData<Account>();
		
		Account account = accountService.get(accountId);
		resultData.setData(account);
		return resultData;
	}
	
	@ApiOperation("根据主键删除指定记录-delete方式")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "accountId", dataType = "Long",required=true,value="主键",defaultValue="")
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@DeleteMapping(value = "/{accountId}")
	@ResponseBody
	public ResultData<Integer> delete(@PathVariable("accountId") Long accountId ) throws Exception {
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = accountService.delete(accountId);	
		
		resultData.setData(new Integer(count));
		
		return resultData;
	}
	
	@ApiOperation("新增业务实体-POST方式")
	@ApiImplicitParams({
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@PostMapping(value = "/add",produces="application/json;charset=utf-8")
	@ResponseBody
	public ResultData<Integer> add(@Validated @RequestBody Account account,BindingResult result) throws Exception {
		//检查校验结果
		if(result != null && result.hasErrors()){
			throw new ValidationException(result);
		}
		
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = accountService.insert(account);	
		
		resultData.setData(new Integer(count));
		
		return resultData;
	}
	
	@ApiOperation("修改业务实体-PUT方式")
	@ApiImplicitParams({
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@PutMapping(value = "/edit",produces="application/json;charset=utf-8")
	@ResponseBody
	public ResultData<Integer> edit(@Validated @RequestBody Account account,BindingResult result) throws Exception {
		//检查校验结果
		if(result != null && result.hasErrors()){
			throw new ValidationException(result);
		}
		
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = accountService.update(account);	
		
		resultData.setData(new Integer(count));
		
		return resultData;
	}
	
	@ApiOperation("获取符合条件的实体列表，按指定属性排序-Post方式")
	@ApiImplicitParams({
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@PostMapping(value = "/getlist")
	@ResponseBody
	public ResultData<List<Account>> getList(@RequestBody FormData<Account> form) throws Exception {
		//返回统一的服务端数据对象
		ResultData<List<Account>> resultData = new ResultData<List<Account>>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		//设置查询条件
		map.put("accountId",form.getBo().getAccountId());
		
		List<Account> list = accountService.getList(map,form.getSort(),form.getOrder());
		
		resultData.setData(list);
		
		return resultData;
	}
	
	@ApiOperation("获取符合条件的分页记录，包括当页数据和记录总数，指定属性排序-POST方式")
	@ApiImplicitParams({
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@PostMapping(value = "/getpage")
	@ResponseBody
	public ResultData<PageRows<Account>> getPage(@RequestBody FormData<Account> form) throws Exception {
		
		//返回统一的服务端数据对象
		ResultData<PageRows<Account>> resultData = new ResultData<PageRows<Account>>();

		Map<String,Object> map = new HashMap<String,Object>();
		
		//设置查询条件
		map.put("accountId",form.getBo().getAccountId());

		long total = accountService.getCount(map,form.getSort(),form.getOrder());
		
		List<Account> list = accountService.getPage(map,form.getSort(),form.getOrder(),form.getPage(),form.getRows());

		PageRows<Account> page = new PageRows<Account>();
		
		page.setCurrent(form.getPage());
		page.setTotal(total);
		page.setRows(list);
		resultData.setData(page);
		return resultData;
	}

}
