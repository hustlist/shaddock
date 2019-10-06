package com.list.shaddock.common.aop;

import com.list.shaddock.common.consts.SysGlobalConst;
import com.list.shaddock.common.datasource.DatabaseContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class DataSourceAspec {
	
	/**
	 * 让业务层方法做aop拦截，便于在执行业务层方法前切换数据源
	 * @param point
	 */
	@Before("execution(* com.list..service..*.*(..))")
	public void setDataSourceKey(JoinPoint point) {
		
		String tenantId = null;
		
		ServletRequestAttributes servletReqAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(servletReqAttr != null) {
			HttpServletRequest request = servletReqAttr.getRequest();
			tenantId = request.getHeader(SysGlobalConst.HTTP_HEADER_X_TENANT_ID);
		}
		
		DatabaseContextHolder.setDatabaseType(DatabaseContextHolder.CMS_DB);
	}
	
	/**
	 * 让业务层方法做aop拦截，清理到数据源
	 * @param point
	 */
	@After("execution(* com.list..service..*.*(..))")
	public void after(JoinPoint point) {
		DatabaseContextHolder.clear();
	}

}
