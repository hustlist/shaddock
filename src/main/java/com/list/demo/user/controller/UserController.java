package com.list.demo.user.controller;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.list.demo.user.model.*;
import com.list.demo.user.service.*;

import com.list.shaddock.common.vo.PageRows;
import com.list.shaddock.common.vo.ResultData;
import com.list.shaddock.common.vo.FormData;
import com.list.shaddock.common.locale.LocalMessageSourceBean;
import com.list.shaddock.common.exception.ValidationException;

/*
 * spring mvc控制类
 */
@Api("User相关api")
@RestController
@RequestMapping(value = "/demo/user")
public class UserController {

	//日志对象
	private static final Logger log = LogManager.getLogger(UserController.class);
	
	//本身服务对象
	@Autowired
	private IUserService userService = null;
	
	//多语言对象
	@Autowired
	private LocalMessageSourceBean lms = null;
	
	@ApiOperation("根据逐渐获取实体对象-get方式")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "id", dataType = "Integer",required=true,value="",defaultValue="")
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResultData<User> get(@PathVariable("id") Integer id ) throws Exception {
		//返回统一的服务端数据对象
		ResultData<User> resultData = new ResultData<User>();
		
		User user = userService.get(id);
		resultData.setData(user);
		return resultData;
	}
	
	@ApiOperation("根据主键删除指定记录-delete方式")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "id", dataType = "Integer",required=true,value="",defaultValue="")
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public ResultData<Integer> delete(@PathVariable("id") Integer id ) throws Exception {
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = userService.delete(id);	
		
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
	public ResultData<Integer> add(@Validated @RequestBody User user,BindingResult result) throws Exception {
		//检查校验结果
		if(result != null && result.hasErrors()){
			throw new ValidationException(result);
		}
		
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = userService.insert(user);	
		
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
	public ResultData<Integer> edit(@Validated @RequestBody User user,BindingResult result) throws Exception {
		//检查校验结果
		if(result != null && result.hasErrors()){
			throw new ValidationException(result);
		}
		
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = userService.update(user);	
		
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
	public ResultData<List<User>> getList(@RequestBody FormData<User> form) throws Exception {
		//返回统一的服务端数据对象
		ResultData<List<User>> resultData = new ResultData<List<User>>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		//设置查询条件
		map.put("id",form.getBo().getId());
		
		List<User> list = userService.getList(map,form.getSort(),form.getOrder());
		
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
	public ResultData<PageRows<User>> getPage(@RequestBody FormData<User> form) throws Exception {
		
		//返回统一的服务端数据对象
		ResultData<PageRows<User>> resultData = new ResultData<PageRows<User>>();

		Map<String,Object> map = new HashMap<String,Object>();
		
		//设置查询条件
		map.put("id",form.getBo().getId());

		long total = userService.getCount(map,form.getSort(),form.getOrder());
		
		List<User> list = userService.getPage(map,form.getSort(),form.getOrder(),form.getPage(),form.getRows());

		PageRows<User> page = new PageRows<User>();
		
		page.setCurrent(form.getPage());
		page.setTotal(total);
		page.setRows(list);
		resultData.setData(page);
		return resultData;
	}

}
