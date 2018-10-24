package ${boInfo.packageName}.controller;

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

import ${boInfo.packageName}.model.*;
import ${boInfo.packageName}.service.*;

import com.list.shaddock.common.vo.PageRows;
import com.list.shaddock.common.vo.ResultData;
import com.list.shaddock.common.vo.FormData;
import com.list.shaddock.common.locale.LocalMessageSourceBean;
import com.list.shaddock.common.exception.ValidationException;

/*
 * spring mvc控制类
 */
@Api("${boInfo.boName}相关api")
@RestController
@RequestMapping(value = "/<@lowerAC>${boInfo.compName}</@lowerAC>/<@lowerAC>${boInfo.boName}</@lowerAC>")
public class ${boInfo.controllerName} {

	//日志对象
	private static final Logger log = LogManager.getLogger(${boInfo.controllerName}.class);
	
	//本身服务对象
	@Autowired
	private ${boInfo.serviceInfName} <@lowerFC>${boInfo.serviceName}</@lowerFC> = null;
	
	//多语言对象
	@Autowired
	private LocalMessageSourceBean lms = null;
	
	@ApiOperation("根据逐渐获取实体对象-get方式")
	@ApiImplicitParams({
<#list boInfo.pks as field>
		@ApiImplicitParam(paramType = "path", name = "${field.fieldName}", dataType = "${field.fieldType}",required=true,value="${field.dbFieldComment}",defaultValue="")
</#list>
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@GetMapping(value = "/<#list boInfo.pks as pkfield>{${pkfield.fieldName}}<#if pkfield_has_next>/</#if></#list>")
	@ResponseBody
	public ResultData<${boInfo.boName}> get(<#list boInfo.pks as pkfield>@PathVariable("${pkfield.fieldName}") ${pkfield.fieldType} ${pkfield.fieldName} <#if pkfield_has_next>,</#if></#list>) throws Exception {
		//返回统一的服务端数据对象
		ResultData<${boInfo.boName}> resultData = new ResultData<${boInfo.boName}>();
		
		${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC> = <@lowerFC>${boInfo.serviceName}</@lowerFC>.get(<#list boInfo.pks as pkfield>${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>);
		resultData.setData(<@lowerFC>${boInfo.boName}</@lowerFC>);
		return resultData;
	}
	
	@ApiOperation("根据主键删除指定记录-delete方式")
	@ApiImplicitParams({
<#list boInfo.pks as field>
		@ApiImplicitParam(paramType = "path", name = "${field.fieldName}", dataType = "${field.fieldType}",required=true,value="${field.dbFieldComment}",defaultValue="")
</#list>
	})	
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "请求参数没填好"), 
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
		 })
	@DeleteMapping(value = "/<#list boInfo.pks as pkfield>{${pkfield.fieldName}}<#if pkfield_has_next>/</#if></#list>")
	@ResponseBody
	public ResultData<Integer> delete(<#list boInfo.pks as pkfield>@PathVariable("${pkfield.fieldName}") ${pkfield.fieldType} ${pkfield.fieldName} <#if pkfield_has_next>,</#if></#list>) throws Exception {
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = <@lowerFC>${boInfo.serviceName}</@lowerFC>.delete(<#list boInfo.pks as pkfield>${pkfield.fieldName}<#if pkfield_has_next>,</#if></#list>);	
		
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
	public ResultData<Integer> add(@Validated @RequestBody ${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>,BindingResult result) throws Exception {
		//检查校验结果
		if(result != null && result.hasErrors()){
			throw new ValidationException(result);
		}
		
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = <@lowerFC>${boInfo.serviceName}</@lowerFC>.insert(<@lowerFC>${boInfo.boName}</@lowerFC>);	
		
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
	public ResultData<Integer> edit(@Validated @RequestBody ${boInfo.boName} <@lowerFC>${boInfo.boName}</@lowerFC>,BindingResult result) throws Exception {
		//检查校验结果
		if(result != null && result.hasErrors()){
			throw new ValidationException(result);
		}
		
		//返回统一的服务端数据对象
		ResultData<Integer> resultData = new ResultData<Integer>();

		int count = <@lowerFC>${boInfo.serviceName}</@lowerFC>.update(<@lowerFC>${boInfo.boName}</@lowerFC>);	
		
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
	public ResultData<List<${boInfo.boName}>> getList(@RequestBody FormData<${boInfo.boName}> form) throws Exception {
		//返回统一的服务端数据对象
		ResultData<List<${boInfo.boName}>> resultData = new ResultData<List<${boInfo.boName}>>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		//设置查询条件
		<#list boInfo.pks as pkfield>
		map.put("${pkfield.fieldName}",form.getBo().get<@upperFC>${pkfield.fieldName}</@upperFC>());
		</#list>
		
		List<${boInfo.boName}> list = <@lowerFC>${boInfo.serviceName}</@lowerFC>.getList(map,form.getSort(),form.getOrder());
		
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
	public ResultData<PageRows<${boInfo.boName}>> getPage(@RequestBody FormData<${boInfo.boName}> form) throws Exception {
		
		//返回统一的服务端数据对象
		ResultData<PageRows<${boInfo.boName}>> resultData = new ResultData<PageRows<${boInfo.boName}>>();

		Map<String,Object> map = new HashMap<String,Object>();
		
		//设置查询条件
		<#list boInfo.pks as pkfield>
		map.put("${pkfield.fieldName}",form.getBo().get<@upperFC>${pkfield.fieldName}</@upperFC>());
		</#list>

		long total = <@lowerFC>${boInfo.serviceName}</@lowerFC>.getCount(map,form.getSort(),form.getOrder());
		
		List<${boInfo.boName}> list = <@lowerFC>${boInfo.serviceName}</@lowerFC>.getPage(map,form.getSort(),form.getOrder(),form.getPage(),form.getRows());

		PageRows<${boInfo.boName}> page = new PageRows<${boInfo.boName}>();
		
		page.setCurrent(form.getPage());
		page.setTotal(total);
		page.setRows(list);
		resultData.setData(page);
		return resultData;
	}

}
